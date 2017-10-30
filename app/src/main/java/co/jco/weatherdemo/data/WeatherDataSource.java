package co.jco.weatherdemo.data;


import java.util.List;

public interface WeatherDataSource {

    void getCities(WeatherCallback<List<WeatherCity>> callback);

    void addCity(String city, WeatherCallback<List<WeatherCity>> callback);

    void getForecast(String city, WeatherCallback<WeatherForecast> callback);

    void removeCity(String city, WeatherCallback<List<WeatherCity>> callback);
}
