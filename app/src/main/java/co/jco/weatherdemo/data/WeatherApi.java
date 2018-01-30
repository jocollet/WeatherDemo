package co.jco.weatherdemo.data;


import javax.inject.Inject;

/**
 * Weather Api Singleton, providing data
 */
public final class WeatherApi {
    private WeatherRepository mRepository;

    @Inject
    WeatherApi(WeatherRepository repository) {
        mRepository = repository;
    }

    public WeatherDataSource getDataSource() {
        return mRepository;
    }

}
