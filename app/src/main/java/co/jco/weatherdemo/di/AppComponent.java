package co.jco.weatherdemo.di;


import javax.inject.Singleton;

import co.jco.weatherdemo.WeatherApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<WeatherApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherApplication> {
    }
}
