package co.jco.weatherdemo.data.local

import co.jco.weatherdemo.data.*

import co.jco.weatherdemo.data.local.beans.RealmWeatherCity
import io.realm.Realm

/**
 * Local WeatherDataSource, facade to Realm database
 * Unused at the moment
 */
class WeatherLocalDataSource : WeatherDataSource {
    override fun getCities(callback: WeatherCallback<List<WeatherCity>>) {
        throw UnsupportedOperationException("Implement me !")
    }

    override fun addCity(city: String, callback: WeatherCallback<WeatherCity>) {
        throw UnsupportedOperationException("Implement me !")
    }

    override fun saveCity(city: WeatherCity, callback: WeatherCallback<WeatherCity>) {
        throw UnsupportedOperationException("Implement me !")

    }

    override fun getForecast(city: String, callback: WeatherCallback<WeatherForecast>) {
        throw UnsupportedOperationException("Implement me !")
    }

    override fun removeCity(city: String, callback: WeatherCallback<WeatherCity>) {
        throw UnsupportedOperationException("Implement me !")
    }
}
