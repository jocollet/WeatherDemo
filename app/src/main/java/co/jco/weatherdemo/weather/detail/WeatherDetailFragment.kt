package co.jco.weatherdemo.weather.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.jco.weatherdemo.R
import kotlinx.android.synthetic.main.fragment_weather_detail.*

/**
 * Weather detail of a city
 */
class WeatherDetailFragment : Fragment() {
    private var mWeatherCityId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mWeatherCityId = arguments.getString(WEATHER_CITY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_weather_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        // widget auto bound by kotlin-android-extensions
        randomTextView.text = "A random text !"
    }

    companion object Factory {
        private val WEATHER_CITY_ID = "weather_city_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(weatherCityId: String): WeatherDetailFragment {
            val fragment = WeatherDetailFragment()
            val args = Bundle()
            args.putString(WEATHER_CITY_ID, weatherCityId)
            fragment.arguments = args
            return fragment
        }
    }
}
