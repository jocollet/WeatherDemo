package co.jco.weatherdemo.data.remote;


import java.util.List;

import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherForecast;
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
    private static final String API_KEY = "";

    // Retrofit service, used to call endpoints
    private final RetrofitOpenWeatherMapInterface mWeatherService;

    public WeatherRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherService = retrofit.create(RetrofitOpenWeatherMapInterface.class);
    }

    @Override
    public void getCities(WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException("Get cities from the local datasource !");
    }

    @Override
    public void addCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        mWeatherService.get(API_KEY, city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                // TODO this response comes back to repository and is stored into local datasource
                // TODO repository needs to implement a caching/ forcerefresh strategy
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

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
