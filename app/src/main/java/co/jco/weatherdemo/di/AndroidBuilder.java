package co.jco.weatherdemo.di;


import co.jco.weatherdemo.weather.home.WeatherActivity;
import co.jco.weatherdemo.weather.home.WeatherActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class AndroidBuilder {

    @ContributesAndroidInjector(modules = WeatherActivityModule.class)
    abstract WeatherActivity weatherActivityInjector();

    //TODO WS4 4 Inject a presenter into WeatherFragment, tip use @ContributesAndroidInjector as done for WeatherActivity

}
