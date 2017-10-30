package co.jco.weatherdemo.weather.home;

import java.util.List;

import co.jco.weatherdemo.base.BasePresenter;
import co.jco.weatherdemo.base.BaseView;
import co.jco.weatherdemo.data.WeatherCity;

/**
 * MVP Contract of the View and the Presenter
 */
public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void showEmpty();

        void showError();

        void showCities(List<WeatherCity> cities);
    }

    interface Presenter extends BasePresenter {

        void addCity(String city);

        void removeCity(String city);
    }
}