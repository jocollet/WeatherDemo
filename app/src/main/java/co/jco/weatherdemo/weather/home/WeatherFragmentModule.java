package co.jco.weatherdemo.weather.home;

import co.jco.weatherdemo.data.WeatherApi;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class WeatherFragmentModule {

    @Provides
    static WeatherContract.Presenter provideWeatherPresenter(WeatherApi weatherApi) {
        return new WeatherPresenterImpl(weatherApi);
    }

}
