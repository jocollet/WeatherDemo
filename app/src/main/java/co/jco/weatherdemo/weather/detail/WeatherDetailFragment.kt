package co.jco.weatherdemo.weather.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.jco.weatherdemo.R

/**
 * Weather detail of a city
 * At the moment it is not reachable
 */
class WeatherDetailFragment : Fragment() {

    //TODO WS3 add some widgets in this fragment layout, then use import kotlinx.android.synthetic.main.fragment_weather_detail.*
    //TODO WS3 to access them without findViewById, by using their ids directly ie myTextViewId.text = "new text"

    private var mWeatherCityId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mWeatherCityId = arguments.getString(WEATHER_CITY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_detail, container, false)
    }

    companion object {
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
}// Required empty public constructor
