package co.jco.weatherdemo.weather.home;

import java.util.List;

import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherRepository;

/**
 * Weather Presenter implementation
 */
public class WeatherPresenterImpl implements WeatherContract.Presenter {

    private WeatherContract.View mView;
    private WeatherRepository mRepository;

    public WeatherPresenterImpl(WeatherContract.View view) {
        mView = view;
        mRepository = new WeatherRepository();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void addCity(String city) {
        mRepository.addCity(city, new WeatherCallback<List<WeatherCity>>() {
            @Override
            public void onResponse(List<WeatherCity> cities, boolean success) {
                if (mView.isActive()) {
                    if (success) {
                        mView.showCities(cities);
                    } else {
                        mView.showError();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (mView.isActive()) {
                    mView.showError();
                }
            }
        });
    }

    @Override
    public void removeCity(String city) {
        mRepository.removeCity(city, new WeatherCallback<List<WeatherCity>>() {
            @Override
            public void onResponse(List<WeatherCity> weatherCities, boolean success) {
                if(mView.isActive()) {
                    if (success) {
                        mView.showCities(weatherCities);
                    } else {
                        mView.showError();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
