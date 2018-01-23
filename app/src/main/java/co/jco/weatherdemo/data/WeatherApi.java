package co.jco.weatherdemo.data;


import javax.inject.Inject;

/**
 * Weather Api Singleton, providing data
 */
public final class WeatherApi {
    private static WeatherApi sInstance = new WeatherApi();
    private WeatherRepository mRepository;

    public static WeatherApi getInstance() {
        return sInstance;
    }

    private WeatherApi() {
        mRepository = new WeatherRepository();
    }

    public WeatherDataSource getDataSource() {
        return mRepository;
    }

}
