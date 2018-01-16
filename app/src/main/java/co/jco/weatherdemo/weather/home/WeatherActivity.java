package co.jco.weatherdemo.weather.home;

import android.os.Bundle;

import javax.inject.Inject;

import co.jco.weatherdemo.R;
import dagger.android.support.DaggerAppCompatActivity;

import static co.jco.weatherdemo.UtilsKt.replaceFragment;

/**
 * "Home" Activity, its fragment displays the main view of the application
 */
public class WeatherActivity extends DaggerAppCompatActivity {

    WeatherFragment mWeatherFragment = WeatherFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        replaceFragment(this, mWeatherFragment, R.id.fl_fragment_container);
    }

}
