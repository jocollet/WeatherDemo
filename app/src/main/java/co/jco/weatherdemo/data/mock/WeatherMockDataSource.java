package co.jco.weatherdemo.data.mock;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherForecast;

/**
 * WeatherDataSource implementation returning mocked data
 * Useful to start development and implement unit tests
 */
public class WeatherMockDataSource implements WeatherDataSource {

    private final Random mRandom = new Random();
    private final List<WeatherCity> mCities = new ArrayList<>();

    private static final String[] PLACEHOLDERS = {"Lyon", "Paris", "Berlin", "Toulouse", "Madrid", "Lausanne",
            "Berne", "Coutances", "Granville", "Chausey", "Luosto"};

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        callback.onResponse(mCities, true);
    }

    @Override
    public void addCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        mCities.add(generateRandomWeather());
        callback.onResponse(mCities, true);
    }

    @Override
    public void getForecast(String cityName, WeatherCallback<WeatherForecast> callback) {
    }

    @Override
    public void removeCity(String cityName, WeatherCallback<List<WeatherCity>> callback) {
        WeatherCity cityToRemove = null;
        for (WeatherCity city : mCities) {
            if (city.getCityName().equals(cityName)) {
                cityToRemove = city;
                break;
            }
        }
        mCities.remove(cityToRemove);
        callback.onResponse(mCities, true);
    }

    private WeatherCity generateRandomWeather() {
        WeatherCity weatherCity = new WeatherCity();
        weatherCity.setCityName(PLACEHOLDERS[mRandom.nextInt(PLACEHOLDERS.length - 1)]);
        weatherCity.setDescription("very accurate description");
        weatherCity.setTemperature(mRandom.nextFloat() * 40f);
        weatherCity.setWeatherCode(mRandom.nextInt(999));

        return weatherCity;
    }

}
