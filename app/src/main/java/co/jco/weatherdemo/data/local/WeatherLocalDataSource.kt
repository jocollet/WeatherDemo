package co.jco.weatherdemo.data.local

import co.jco.weatherdemo.data.*
import co.jco.weatherdemo.data.local.beans.RealmWeatherCity
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Named

/**
 * Local WeatherDataSource, facade to Realm database
 * Unused at the moment
 */
@Named("localWeather")
class WeatherLocalDataSource @Inject constructor() : WeatherDataSource {

    override fun getCities(callback: WeatherCallback<List<WeatherCity>>) {
        val realm = Realm.getDefaultInstance()
        val cities = realm.where(RealmWeatherCity::class.java).findAllSorted(RealmWeatherCity.CITY_NAME).map { it.fromRealmExt() }
        realm.close()
        callback.onResponse(cities, true)
    }

    override fun addCity(city: String, callback: WeatherCallback<WeatherCity>) {
        throw UnsupportedOperationException("Implement me !")
    }

    override fun saveCity(city: WeatherCity, callback: WeatherCallback<WeatherCity>) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(city.toRealmExt())
        }
        callback.onResponse(city, true)
    }

    override fun getForecast(city: String, callback: WeatherCallback<WeatherForecast>) {
        throw UnsupportedOperationException("Implement me !")
    }

    override fun removeCity(city: String, callback: WeatherCallback<WeatherCity>) {
        val realm = Realm.getDefaultInstance()
        var removed: RealmWeatherCity?
        var weatherCity: WeatherCity? = null
        realm.executeTransaction {
            removed = realm.where(RealmWeatherCity::class.java).equalTo(RealmWeatherCity.CITY_NAME, city).findFirst()
            weatherCity = removed?.fromRealmExt()
            removed?.deleteFromRealm()
        }
        callback.onResponse(weatherCity, weatherCity != null)
    }
}
