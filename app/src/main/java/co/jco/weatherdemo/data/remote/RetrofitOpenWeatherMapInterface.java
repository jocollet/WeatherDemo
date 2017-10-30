package co.jco.weatherdemo.data.remote;

import co.jco.weatherdemo.data.remote.beans.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface to use OpenWeatherMap endpoints
 */
public interface RetrofitOpenWeatherMapInterface {

    /**
     * Get the current weather
     * @param apiKey OpenWeatherMap api key, required to use endpoints
     * @param city the city we want to know the weather
     * @return a Call representing the query
     */
    @GET("weather")
    Call<WeatherResponse> get(@Query("APPID") String apiKey, @Query("q") String city);
}
