package co.jco.weatherdemo.data.remote;

import co.jco.weatherdemo.data.remote.beans.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitOpenWeatherMapInterface {

    @GET("weather")
    Call<WeatherResponse> get(@Query("APPID") String apiKey, @Query("q") String city);
}
