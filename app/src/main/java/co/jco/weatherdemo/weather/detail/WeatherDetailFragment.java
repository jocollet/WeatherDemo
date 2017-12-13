package co.jco.weatherdemo.weather.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.jco.weatherdemo.R;

/**
 * Weather detail of a city
 * At the moment it is not reachable
 */
public class WeatherDetailFragment extends Fragment {
    private static final String WEATHER_CITY_ID = "weather_city_id";

    private String mWeatherCityId;

    public WeatherDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BlankFragment.
     */
    public static WeatherDetailFragment newInstance(String weatherCityId) {
        WeatherDetailFragment fragment = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putString(WEATHER_CITY_ID, weatherCityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWeatherCityId = getArguments().getString(WEATHER_CITY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

}
