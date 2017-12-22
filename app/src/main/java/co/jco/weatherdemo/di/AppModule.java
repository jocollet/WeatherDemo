package co.jco.weatherdemo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.jco.weatherdemo.weather.home.WeatherActivityComponent;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {
        WeatherActivityComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}