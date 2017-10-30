package co.jco.weatherdemo.data;


import java.util.List;

import co.jco.weatherdemo.data.local.WeatherLocalDataSource;
import co.jco.weatherdemo.data.mock.WeatherMockDataSource;
import co.jco.weatherdemo.data.remote.WeatherRemoteDataSource;

/**
 * Unique access to the DataSources, it orchestrates all calls and implements caching policy and
 * refreshes data if needed.
 * At the moment it serves fake data of the #WeatherMockDataSource
 */
public class WeatherRepository implements WeatherDataSource {

    private final WeatherLocalDataSource mLocalDataSource;
    private final WeatherRemoteDataSource mRemoteDataSource;
    private final WeatherMockDataSource mFakeDataSource;

    /**
     * Instanciates a new #WeatherRepository, you should only have one instance
     */
    public WeatherRepository() {
        mLocalDataSource = new WeatherLocalDataSource();
        mRemoteDataSource = new WeatherRemoteDataSource();
        mFakeDataSource = new WeatherMockDataSource();
    }

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        mFakeDataSource.getCities(callback);
    }

    @Override
    public void addCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        mFakeDataSource.addCity(city, callback);
    }

    @Override
    public void getForecast(String city, WeatherCallback<WeatherForecast> callback) {
        throw new UnsupportedOperationException("Implements me !");
    }

    @Override
    public void removeCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        mFakeDataSource.removeCity(city, callback);
    }

}
