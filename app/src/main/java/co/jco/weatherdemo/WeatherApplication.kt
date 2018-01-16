package co.jco.weatherdemo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(getRealmConfiguration())
    }

    private fun getRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
    }
}