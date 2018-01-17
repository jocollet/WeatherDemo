package co.jco.weatherdemo.data;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Unique access to the DataSources, it orchestrates all calls and implements caching policy and
 * refreshes data if needed.
 * At the moment it serves fake data of the #WeatherMockDataSource
 */
public class WeatherRepository implements WeatherDataSource {

    private WeatherDataSource mLocalDataSource;
    private WeatherDataSource mRemoteDataSource;
    private WeatherDataSource mFakeDataSource;

    private boolean withRemote = false;

    public boolean isWithRemote() {
        return withRemote;
    }

    public void withRemote(boolean remoteEnabled) {
        this.withRemote = remoteEnabled;
    }

     /**
     * Instanciates a new #WeatherRepository, you should only have one instance
     */
    @Inject
    public WeatherRepository(@Named("localDataSource") WeatherDataSource localDataSource,
                             @Named("remoteDataSource") WeatherDataSource remoteDataSource,
                             @Named("fakeDataSource") WeatherDataSource fakeDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
        mFakeDataSource = fakeDataSource;
    }

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        mLocalDataSource.getCities(callback);
    }

    @Override
    public void addCity(String city, final WeatherCallback<WeatherCity> callback) {

        WeatherCallback<WeatherCity> localCallback = new WeatherCallback<WeatherCity>() {
            @Override
            public void onResponse(WeatherCity weatherCity, boolean success) {
                if (success) {
                    mLocalDataSource.saveCity(weatherCity, callback);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };

        if (withRemote) {
            mRemoteDataSource.addCity(city, localCallback);
        } else {
            mFakeDataSource.addCity(city, localCallback);
        }
    }

    @Override
    public void saveCity(WeatherCity city, WeatherCallback<WeatherCity> callback) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void getForecast(String city, WeatherCallback<WeatherForecast> callback) {
        throw new UnsupportedOperationException("Implements me !");
    }

    @Override
    public void removeCity(String city, WeatherCallback<WeatherCity> callback) {
        mLocalDataSource.removeCity(city, callback);
    }

}