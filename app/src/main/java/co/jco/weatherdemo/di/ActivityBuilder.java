package co.jco.weatherdemo.di;


import android.app.Activity;

import co.jco.weatherdemo.weather.home.WeatherActivity;
import co.jco.weatherdemo.weather.home.WeatherActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(WeatherActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindWeatherActivity(WeatherActivityComponent.Builder builder);

}
