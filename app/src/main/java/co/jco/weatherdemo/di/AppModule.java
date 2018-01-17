package co.jco.weatherdemo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import co.jco.weatherdemo.data.WeatherDataSource;
import co.jco.weatherdemo.data.WeatherRepository;
import co.jco.weatherdemo.data.local.WeatherLocalDataSource;
import co.jco.weatherdemo.data.mock.WeatherMockDataSource;
import co.jco.weatherdemo.data.remote.WeatherRemoteDataSource;
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
    static WeatherRepository provideWeatherRepository(@Named("localDataSource")WeatherDataSource localDataSource,
                                                      @Named("remoteDataSource")WeatherDataSource remoteDataSource,
                                                      @Named("fakeDataSource")WeatherDataSource fakeDataSource) {
        return new WeatherRepository(localDataSource, remoteDataSource, fakeDataSource);
    }

    @Provides
    @Singleton
    @Named("localDataSource")
    static WeatherDataSource provideLocalDataSource() {
        return new WeatherLocalDataSource();
    }

    @Provides
    @Singleton
    @Named("remoteDataSource")
    static WeatherDataSource provideRemoteDataSource() {
        return new WeatherRemoteDataSource();
    }

    @Provides
    @Singleton
    @Named("fakeDataSource")
    static WeatherDataSource provideFakeDataSource() {
        return new WeatherMockDataSource(true);
    }

}