package co.jco.weatherdemo.data

import co.jco.weatherdemo.data.remote.beans.WeatherResponse

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