package co.jco.weatherdemo.data

import co.jco.weatherdemo.data.local.beans.RealmWeatherCity
import co.jco.weatherdemo.data.remote.beans.WeatherResponse

fun toRealm(city: WeatherCity): RealmWeatherCity {
    val realmCity = RealmWeatherCity()
    realmCity.cityName = city.cityName
    realmCity.description = city.description
    realmCity.weatherCode = city.weatherCode
    realmCity.temperature = city.temperature
    return realmCity
}

fun WeatherCity.toRealmExt() : RealmWeatherCity {
    val realmCity = RealmWeatherCity()
    realmCity.cityName = this.cityName
    realmCity.description = this.description
    realmCity.weatherCode = this.weatherCode
    realmCity.temperature = this.temperature
    return realmCity
}

fun fromRealm(realmCity: RealmWeatherCity): WeatherCity {
    val city = WeatherCity()
    city.cityName = realmCity.cityName
    city.description = realmCity.description
    city.temperature = realmCity.temperature
    city.weatherCode = realmCity.weatherCode
    return city
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