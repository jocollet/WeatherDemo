package co.jco.weatherdemo.weather.home;

import java.util.List;

import co.jco.weatherdemo.data.WeatherApi;
import co.jco.weatherdemo.data.WeatherCallback;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherDataSource;

/**
 * WeatherPresenter implementation, it orchestrates View and Model interactions.
 */
public class WeatherPresenterImpl implements WeatherContract.Presenter {

    /**
     * The View it is attached to
     */
    private WeatherContract.View mView;
    /**
     * The repository from which it manipulates data
     */
    private WeatherDataSource mDataSource;

    public WeatherPresenterImpl(WeatherContract.View view) {
        mView = view;
        mDataSource = WeatherApi.getInstance().getDataSource();
        mView.setPresenter(this);
    }

    /**
     * At the moment it is not really useful, but it will be when data will be fetched from the local
     * datasource
     */
    @Override
    public void start() {
        mDataSource.getCities(new WeatherCallback<List<WeatherCity>>() {
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
    public void addCity(String cityName) {
        mDataSource.addCity(cityName, new WeatherCallback<List<WeatherCity>>() {
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
        mDataSource.removeCity(city, new WeatherCallback<List<WeatherCity>>() {
            @Override
            public void onResponse(List<WeatherCity> weatherCities, boolean success) {
                if (mView.isActive()) {
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
