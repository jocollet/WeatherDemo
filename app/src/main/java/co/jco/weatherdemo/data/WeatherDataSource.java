package co.jco.weatherdemo.data;


import java.util.List;

/**
 * Interface implemented by the #WeatherRepository and all its DataSources
 * Some methods might not be available for certain DataSources
 */
public interface WeatherDataSource {

    void getCities(WeatherCallback<List<WeatherCity>> callback);

    void addCity(String city, WeatherCallback<WeatherCity> callback);

    void saveCity(WeatherCity city, WeatherCallback<WeatherCity> callback);

    void removeCity(String city, WeatherCallback<WeatherCity> callback);

    void getForecast(String city, WeatherCallback<WeatherForecast> callback);
}
