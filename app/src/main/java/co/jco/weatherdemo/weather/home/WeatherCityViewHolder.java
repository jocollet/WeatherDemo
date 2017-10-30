package co.jco.weatherdemo.weather.home;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Locale;

import co.jco.weatherdemo.R;
import co.jco.weatherdemo.data.WeatherCity;


public class WeatherCityViewHolder extends RecyclerView.ViewHolder {
    public AppCompatTextView mCityName;
    public AppCompatTextView mTemperature;
    public AppCompatTextView mDescription;
    public AppCompatImageView mSkyImage;

    public WeatherCityViewHolder(View itemView) {
        super(itemView);

        mCityName = itemView.findViewById(R.id.tv_city_name);
        mTemperature = itemView.findViewById(R.id.tv_temperature);
        mDescription = itemView.findViewById(R.id.tv_description);
        mSkyImage = itemView.findViewById(R.id.iv_sky);
    }

    /**
     * Binds a city weather to a viewholder
     * @param weatherCity the city weather to bind
     */
    public void bind(WeatherCity weatherCity) {
        mCityName.setText(weatherCity.getCityName());
        mTemperature.setText(formatTemperature(weatherCity));
        mDescription.setText(weatherCity.getDescription());
        @DrawableRes int skyResource = (weatherCity.getWeatherCode() < 500)
                ? R.drawable.ic_cloud_white_48dp
                : R.drawable.ic_wb_sunny_white_48dp;
        mSkyImage.setImageResource(skyResource);
    }

    private String formatTemperature(WeatherCity weatherCity) {
        return String.format(Locale.US, "%.1f °C", weatherCity.getTemperature());
    }
}
