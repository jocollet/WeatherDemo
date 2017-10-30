package co.jco.weatherdemo.data;


public interface WeatherCallback<T> {

        void onResponse(T t, boolean success);

        void onFailure(Throwable t);
}
