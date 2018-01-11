package co.jco.weatherdemo.data;


import javax.inject.Inject;

/**
 * Weather Api Singleton, providing data
 */
public final class WeatherApi {
    private WeatherRepository mRepository;

    @Inject
    public WeatherApi(WeatherRepository repository) {
        mRepository = repository;
    }

    public WeatherDataSource getDataSource() {
        return mRepository;
    }

}
