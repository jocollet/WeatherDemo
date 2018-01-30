package co.jco.weatherdemo

import co.jco.weatherdemo.di.AppModule
import co.jco.weatherdemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration


class WeatherApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out WeatherApplication> {
        val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        // init Realm, this would be at a better place in the local data source
        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
    }
}