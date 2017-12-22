package co.jco.weatherdemo.weather.home;

import co.jco.weatherdemo.data.WeatherApi;
import dagger.Module;
import dagger.Provides;

@Module
public class WeatherActivityModule {

    @Provides
    WeatherContract.Presenter provideWeatherPresenter(WeatherContract.View view, WeatherApi weatherApi) {
        return new WeatherPresenterImpl(view, weatherApi);
    }

}
