package co.jco.weatherdemo.data;


/**
 * Weather Api Singleton, providing data
 */
public final class WeatherApi {

    private static final WeatherApi sInstance = new WeatherApi();

    private WeatherRepository mRepository;

    public static WeatherApi getInstance() {
        return sInstance;
    }

    /**
     * Hide default constructor, clients must use #getInstance()
     */
    private WeatherApi() {
        mRepository = new WeatherRepository();
    }

    public WeatherDataSource getDataSource() {
        return mRepository;
    }

}
