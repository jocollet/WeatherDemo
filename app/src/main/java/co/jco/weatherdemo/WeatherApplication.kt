package co.jco.weatherdemo

import co.jco.weatherdemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration


class WeatherApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out WeatherApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(getRealmConfiguration())
    }

    private fun getRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
    }
}