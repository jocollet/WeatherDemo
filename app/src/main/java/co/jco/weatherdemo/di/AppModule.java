package co.jco.weatherdemo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import co.jco.weatherdemo.data.WeatherApi;
import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherRepository;
import co.jco.weatherdemo.data.local.WeatherLocalDataSource;
import co.jco.weatherdemo.data.mock.WeatherMockDataSource;
import co.jco.weatherdemo.data.remote.WeatherRemoteDataSource;
import co.jco.weatherdemo.weather.home.WeatherContract;
import co.jco.weatherdemo.weather.home.WeatherPresenterImpl;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public  class AppModule {

    private final Application application;

    public AppModule(Application app) {
        application = app;
    }

    @Provides
    @Singleton
    @Named("ApplicationContext")
    Context provideContext() {
        return application;
    }

    @Provides
    @Named("localWeather")
    WeatherDataSource provideLocalWeatherDataSource() {
        return new WeatherLocalDataSource();
    }

    @Provides
    @Named("remoteWeather")
    WeatherDataSource provideRemoteWeatherDataSource() {
        return new WeatherRemoteDataSource();
    }

    @Provides
    @Named("fakeWeather")
    WeatherDataSource provideLocalFakeDataSource() {
        return new WeatherMockDataSource(true);
    }

}