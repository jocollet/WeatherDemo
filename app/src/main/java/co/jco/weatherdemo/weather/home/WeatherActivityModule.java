package co.jco.weatherdemo.weather.home;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WeatherActivityModule {

    @Provides
    static WeatherFragment provideWeatherFragment() {
        return WeatherFragment.newInstance();
    }

    @ContributesAndroidInjector(modules = WeatherFragmentModule.class)
    abstract WeatherFragment weatherFragmentInjector();

}
