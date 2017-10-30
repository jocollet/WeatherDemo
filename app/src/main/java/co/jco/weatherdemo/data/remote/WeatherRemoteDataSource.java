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

public class WeatherRemoteDataSource implements WeatherDataSource {

    private static final String API_KEY = "";

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
        throw new UnsupportedOperationException();
    }

    @Override
    public void addCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        mWeatherService.get(API_KEY, city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                // TODO this responses comes back to repository, is stored into local datasource, then
                // the updated list is sent using the callback
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getForecast(String city, WeatherCallback<WeatherForecast> callback) {

    }

    @Override
    public void removeCity(String city, WeatherCallback<List<WeatherCity>> callback) {
        throw new UnsupportedOperationException();
    }

}
