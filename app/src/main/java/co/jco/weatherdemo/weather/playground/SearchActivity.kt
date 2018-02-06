package co.jco.weatherdemo.weather.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.jco.weatherdemo.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit


class SearchActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    companion object {
        var counter: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // when a click is performed on buttonTest, then execute subscribeAndPrint() method
        compositeDisposable.add(
                RxView.clicks(buttonTest)
                        .subscribe { _ -> subscribeAndPrint() })
        test()
    }

//    If you want to make a network call on click you have to use observeOn twice.
//    It is because (as Jake said) RxBinding require subscriptions to execute on main thread.
//
//    RxView.clicks(_btn_loadWithRxAndroid)
//    .observeOn(Schedulers.io())
//    .switchMap{ call() }
//    .observeOn(AndroidSchedulers.mainThread())
//    .subscribe(...);

    fun createSearchViewFlowable(): Observable<String> {
        return RxSearchView.queryTextChanges(searchView)
                .observeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map { cs -> cs.toString() }
                .filter { s -> s.length >= 3 }
                .switchMap { query -> networkSearch(query) }
    }

    private fun networkSearch(query: String): Observable<String> {
        return Observable
                .just(true)
                .delay(1, TimeUnit.SECONDS)
                .map { query + "_network" + counter++ }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(
                createSearchViewFlowable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe { s -> textResult.text = s })
    }

    fun test() {
        compositeDisposable.add(
                Observable.just("Hello World")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //observation on the main thread
                        .subscribe({ s -> System.out.println(s) }))
    }

    fun createSubscriber(): DisposableSubscriber<String> {
        val subscriber = object : DisposableSubscriber<String>() {
            override fun onStart() {
                println("start !")
            }
            override fun onComplete() {
                println("complete !")
            }
            override fun onNext(t: String?) {
                println(t)
            }
            override fun onError(t: Throwable?) {
                println("ERROR :-X")
            }
        }
        compositeDisposable.add(subscriber)
        return subscriber
    }

    fun subscribeAndPrint() {
        Flowable.
                just("a", "b", "c", "d", "e")
                .map { s: String -> s.capitalize() }
                .subscribe(createSubscriber())
    }

    public override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
