package co.jco.weatherdemo.data.local.beans

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * This class needs to be open since Realm will extend it to override getters/setters
 */
open class RealmWeatherCity : RealmObject() {

    @PrimaryKey var cityName: String = ""
    /**
     * defined in °C by convention
     */
    var temperature: Double = 0.toDouble()
    /**
     * weather description
     */
    var description: String? = null
    /**
     * weather code, see http://openweathermap.org/weather-conditions
     */
    var weatherCode: Int = 0

    companion object {
        const val CITY_NAME = "cityName"
        const val TEMPERATURE = "temperature"
        const val DESCRIPTION = "description"
        const val WEATHER_CODE = "weatherCode"
    }

}
