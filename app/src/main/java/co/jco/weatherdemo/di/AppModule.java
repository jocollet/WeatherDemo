package co.jco.weatherdemo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.jco.weatherdemo.data.WeatherApi;
import co.jco.weatherdemo.data.WeatherRepository;
import co.jco.weatherdemo.weather.home.WeatherActivity;
import co.jco.weatherdemo.weather.home.WeatherActivityModule;
import co.jco.weatherdemo.weather.home.WeatherFragment;
import co.jco.weatherdemo.weather.home.WeatherFragmentModule;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    static WeatherRepository provideWeatherRepository() {
        return new WeatherRepository();
    }

    @ContributesAndroidInjector(modules = WeatherActivityModule.class)
    abstract WeatherActivity weatherActivityInjector();
}