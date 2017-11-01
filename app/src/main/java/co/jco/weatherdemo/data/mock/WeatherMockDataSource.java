package co.jco.weatherdemo.data.mock;


import java.util.ArrayList;
import java.util.Collections;
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
    private final boolean mCanReturnErrors;

    private static final String[] PLACEHOLDERS = {"Lyon", "Paris", "Berlin", "Toulouse", "Madrid", "Lausanne",
            "Berne", "Coutances", "Granville", "Chausey", "Luosto"};
    private final int mPlaceholderCounter = PLACEHOLDERS.length;

    /**
     * Instanciates a new #WeatherMockDataSource
     *
     * @param canReturnErrors use this boolean to allow errors to be returned, you probably want this
     *                        boolean false while executing unit tests.
     */
    public WeatherMockDataSource(boolean canReturnErrors) {
        mCanReturnErrors = canReturnErrors;
    }

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        callback.onResponse(mCities, true);
    }

    @Override
    public void addCity(String cityName, WeatherCallback<List<WeatherCity>> callback) {
        // Generate a random number between 0 and 20
        int roll = mRandom.nextInt(20);

        // respond an error if the number is <2, a not success response if < 4
        if (mCanReturnErrors && roll < 2) {
            callback.onFailure(new Throwable());
        } else if (mCanReturnErrors && roll >= 2 && roll < 4) {
            callback.onResponse(Collections.<WeatherCity>emptyList(), false);
        } else {
            mCities.add(generateRandomWeather(cityName));
            callback.onResponse(mCities, true);
        }
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

    private WeatherCity generateRandomWeather(String cityName) {
        WeatherCity weatherCity = new WeatherCity();
        if (cityName != null && !cityName.isEmpty()) {
            weatherCity.setCityName(cityName);
        } else {
            weatherCity.setCityName(PLACEHOLDERS[mRandom.nextInt(PLACEHOLDERS.length - 1)]);
        }
        weatherCity.setDescription("very accurate description");
        weatherCity.setTemperature(mRandom.nextFloat() * 40f);
        weatherCity.setWeatherCode(mRandom.nextInt(999));

        return weatherCity;
    }

}
