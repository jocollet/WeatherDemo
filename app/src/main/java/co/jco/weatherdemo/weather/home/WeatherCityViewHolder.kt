package co.jco.weatherdemo.weather.home

import android.support.annotation.DrawableRes
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import co.jco.weatherdemo.R
import co.jco.weatherdemo.data.WeatherCity
import java.util.*


class WeatherCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mCityName: AppCompatTextView = itemView.findViewById(R.id.tv_city_name)
    var mTemperature: AppCompatTextView = itemView.findViewById(R.id.tv_temperature)
    var mDescription: AppCompatTextView = itemView.findViewById(R.id.tv_description)
    var mSkyImage: AppCompatImageView = itemView.findViewById(R.id.iv_sky)

    /**
     * Binds a city weather to a viewholder
     *
     * @param weatherCity the city weather to bind
     */
    fun bind(weatherCity: WeatherCity, listener: (WeatherCity) -> Unit) {
        mCityName.text = weatherCity.cityName
        mTemperature.text = formatTemperature(weatherCity)
        mDescription.text = weatherCity.description
        @DrawableRes val skyResource = if (weatherCity.weatherCode < 500)
            R.drawable.ic_cloud_white_48dp
        else
            R.drawable.ic_wb_sunny_white_48dp
        mSkyImage.setImageResource(skyResource)
        itemView.setOnClickListener({ listener(weatherCity) })
    }

    private fun formatTemperature(weatherCity: WeatherCity): String {
        return String.format(Locale.US, "%.1f °C", weatherCity.temperature)
    }
}
