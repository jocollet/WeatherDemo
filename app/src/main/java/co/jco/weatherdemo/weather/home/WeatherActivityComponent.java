package co.jco.weatherdemo.weather.home;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = WeatherActivityModule.class)
public interface WeatherActivityComponent extends AndroidInjector<WeatherActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherActivity> {
    }
}