package co.jco.weatherdemo.weather.home;

import co.jco.weatherdemo.data.WeatherApi;
import dagger.Module;
import dagger.Provides;

@Module
public class WeatherFragmentModule {

    @Provides
    WeatherContract.Presenter providePresenter(WeatherApi weatherApi) {
        return new WeatherPresenterImpl(weatherApi);
    }


}
