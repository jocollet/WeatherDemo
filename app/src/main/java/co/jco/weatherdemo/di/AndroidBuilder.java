package co.jco.weatherdemo.di;


import co.jco.weatherdemo.weather.home.WeatherActivity;
import co.jco.weatherdemo.weather.home.WeatherActivityModule;
import co.jco.weatherdemo.weather.home.WeatherFragment;
import co.jco.weatherdemo.weather.home.WeatherFragmentModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class AndroidBuilder {

    @ContributesAndroidInjector(modules = WeatherActivityModule.class)
    abstract WeatherActivity weatherActivityInjector();

    @ContributesAndroidInjector(modules = WeatherFragmentModule.class)
    abstract WeatherFragment weatherFragmentInjector();

}
