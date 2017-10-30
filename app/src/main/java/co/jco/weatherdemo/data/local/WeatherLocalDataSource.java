package co.jco.weatherdemo.data.local;

import java.util.List;

import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherForecast;

/**
 * Local WeatherDataSource, facade to Realm database
 * Unused at the moment
 */
public class WeatherLocalDataSource implements WeatherDataSource{
    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Implement me !");
    }

    @Override
    public void addCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Implement me !");
    }

    @Override
    public void getForecast(String city, WeatherCallback<WeatherForecast> callback) {
        throw new UnsupportedOperationException("Implement me !");
    }

    @Override
    public void removeCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Implement me !");
    }
}
