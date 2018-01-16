package co.jco.weatherdemo.weather.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.jco.weatherdemo.R;

import static co.jco.weatherdemo.UtilsKt.replaceFragment;

/**
 * "Home" Activity, its fragment displays the main view of the application
 */
public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        replaceFragment(this, WeatherFragment.newInstance(), R.id.fl_fragment_container);
    }

}
