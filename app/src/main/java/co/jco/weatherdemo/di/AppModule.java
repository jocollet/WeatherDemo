package co.jco.weatherdemo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.jco.weatherdemo.data.WeatherRepository;
import dagger.Module;
import dagger.Provides;

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

}