package co.jco.weatherdemo.data.remote;


import java.util.ArrayList;
import java.util.List;

import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherForecast;
import co.jco.weatherdemo.data.remote.beans.Weather;
import co.jco.weatherdemo.data.remote.beans.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WeatherDataSource remote implementation, it uses OpenWeatherMap endpoints via Retrofit
 * TODO : Add a valid api key
 */
public class WeatherRemoteDataSource implements WeatherDataSource {

    // OpenWeatherMap Api key, mandatory
    private static final String API_KEY = "ee6f6f9398c7a97c351fc96f90aaab59";

    // Retrofit service, used to call endpoints
    private final RetrofitOpenWeatherMapInterface mWeatherService;

    public WeatherRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherService = retrofit.create(RetrofitOpenWeatherMapInterface.class);
    }

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Get cities from the local datasource !");
    }

    @Override
    public void addCity(String city, final WeatherCallback<List<WeatherCity>> callback) {
        mWeatherService.get(API_KEY, city, "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                // TODO this response comes back to repository and is stored into local datasource
                // TODO repository needs to implement a caching/ force refresh strategy

                WeatherResponse body = response.body();

                if (response.isSuccessful() && body != null) {
                    WeatherCity weatherCity = new WeatherCity();
                    weatherCity.setCityName(body.getName());
                    Weather firstWeather = body.getWeather().get(0);
                    weatherCity.setDescription(firstWeather.getDescription());
                    weatherCity.setWeatherCode(firstWeather.getId());
                    weatherCity.setTemperature(body.getMain().getTemp());
                    ArrayList<WeatherCity> list = new ArrayList<>();
                    list.add(weatherCity);
                    callback.onResponse(list, response.isSuccessful());
                } else {
                    callback.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getForecast(String city, WeatherCallback<WeatherForecast> callback) {
        throw new UnsupportedOperationException("Implement me !");
    }

    @Override
    public void removeCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Call local datasource instead !");
    }

}
