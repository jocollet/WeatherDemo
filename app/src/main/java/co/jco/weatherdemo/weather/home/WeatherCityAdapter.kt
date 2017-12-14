package co.jco.weatherdemo.weather.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.jco.weatherdemo.R
import co.jco.weatherdemo.data.WeatherCity
import java.util.*

/**
 * Cities adapter, receives a list of cities and notifies the attached RecyclerView
 */
class WeatherCityAdapter : RecyclerView.Adapter<WeatherCityViewHolder>() {
    //TODO WS3 Add a listener field to this class ! listener signature : (WeatherCity) -> Unit
    private val mCities = ArrayList<WeatherCity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_city, parent, false)
        return WeatherCityViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherCityViewHolder, position: Int) {
        holder.bind(mCities[position])
    }

    override fun getItemCount(): Int {
        return mCities.size
    }

    /**
     * Replaces the current cities by those of the given collection
     *
     * @param cities a collection of cities
     */
    fun setCities(cities: Collection<WeatherCity>) {
        mCities.clear()
        mCities.addAll(cities)
        notifyDataSetChanged()
    }

}
