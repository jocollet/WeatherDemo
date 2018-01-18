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
    @Singleton
    WeatherRepository provideWeatherRepository(@Named("localDataSource")WeatherDataSource localDataSource,
                                                      @Named("remoteDataSource")WeatherDataSource remoteDataSource,
                                                      @Named("fakeDataSource")WeatherDataSource fakeDataSource) {
        return new WeatherRepository(localDataSource, remoteDataSource, fakeDataSource);
    }

    @Provides
    @Singleton
    @Named("localDataSource")
    WeatherDataSource provideLocalDataSource(@Named("ApplicationContext") Context context) {
        return new WeatherLocalDataSource(context);
    }

    @Provides
    @Singleton
    @Named("remoteDataSource")
    WeatherDataSource provideRemoteDataSource() {
        return new WeatherRemoteDataSource();
    }

    @Provides
    @Singleton
    @Named("fakeDataSource")
    WeatherDataSource provideFakeDataSource() {
        return new WeatherMockDataSource(true);
    }

}