package co.jco.weatherdemo.data

import co.jco.weatherdemo.data.local.beans.RealmWeatherCity
import co.jco.weatherdemo.data.remote.beans.WeatherResponse

fun WeatherCity.toRealmExt() : RealmWeatherCity {
    val realmCity = RealmWeatherCity()
    realmCity.cityName = this.cityName
    realmCity.description = this.description
    realmCity.weatherCode = this.weatherCode
    realmCity.temperature = this.temperature
    return realmCity
}

fun RealmWeatherCity.fromRealmExt(): WeatherCity {
    val city = WeatherCity()
    city.cityName = this.cityName
    city.description = this.description
    city.temperature = this.temperature
    city.weatherCode = this.weatherCode
    return city
}

@JvmName("mapToWeatherCity")
fun WeatherResponse.toWeatherCity() : WeatherCity {
    val weatherCity = WeatherCity()
    weatherCity.cityName = this.name
    val firstWeather = this.weather[0]
    weatherCity.description = firstWeather.description
    weatherCity.weatherCode = firstWeather.id
    weatherCity.temperature = this.main.temp
    return weatherCity
}