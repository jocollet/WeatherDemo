package co.jco.weatherdemo.weather.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.jco.weatherdemo.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

/**
 * An Activity with a SearchView bound with Rx
 */
class SearchActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    companion object {
        var counter: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // when a click is performed on buttonTest, then print click !
        compositeDisposable.add(
                RxView.clicks(buttonTest)
                        .subscribe { _ -> println("click !") })
    }

//    If you want to make a network call on click you have to use observeOn twice.
//    It is because (as Jake said) RxBinding require subscriptions to execute on main thread.
//
//    RxView.clicks(_btn_loadWithRxAndroid)
//    .observeOn(Schedulers.io())
//    .switchMap{ call() }
//    .observeOn(AndroidSchedulers.mainThread())
//    .subscribe(...);

    /**
     * //TODO WS5
     * When the user types a query, do :
     * print the query -> use doOnNext
     * switch to I/O scheduler -> use observeOn
     * limit item emission to 500ms minimum -> use debounce
     * do not emit if the query is the same as the last one emitted -> distinctUntilChanged
     * filter to block item with less than 3 characters -> filter
     * perform network call only with last item emitted -> switchMap
     */
    fun createSearchViewFlowable(): Observable<String> {
        return RxSearchView.queryTextChanges(searchView)
                .map { cs -> cs.toString() }
    }

    /**
     * Simulates a network call
     * @return an Observable String with a sequence number
     */
    private fun networkSearch(query: String): Observable<String> {
        return Observable
                .just(true)
                .delay(1, TimeUnit.SECONDS)
                .map { query + "_network" + counter++ }
    }

    override fun onResume() {
        super.onResume()
        /**
         * Put the subscription into the disposable to enable future cleanup
         */
        compositeDisposable.add(
                createSearchViewFlowable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe { s -> textResult.text = s })
    }

    public override fun onDestroy() {
        super.onDestroy()
        /**
         * Clean all subscriptions
         */
        compositeDisposable.dispose()
    }

}
