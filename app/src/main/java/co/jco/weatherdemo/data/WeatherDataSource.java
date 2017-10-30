package co.jco.weatherdemo.data;


import java.util.List;

/**
 * Interface implemented by the #WeatherRepository and all its DataSources
 * Some methods might not be available for certain DataSources
 */
public interface WeatherDataSource {

    void getCities(WeatherCallback<List<WeatherCity>> callback);

    void addCity(String city, WeatherCallback<List<WeatherCity>> callback);

    void getForecast(String city, WeatherCallback<WeatherForecast> callback);

    void removeCity(String city, WeatherCallback<List<WeatherCity>> callback);
}
