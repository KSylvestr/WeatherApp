package com.reactivelabs.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getString(SearchFragment.WEATHER_OVERALL)
        cityTitle.text = arguments?.getString(SearchFragment.CITY)
        title.text = weather
        description.text = arguments?.getString(SearchFragment.WEATHER_DESCRIPTION)
        temperature.text = "${arguments?.getDouble(SearchFragment.TEMP)}°C"
        humidity.text = "${arguments?.getInt(SearchFragment.HUMIDITY)}%"

        val drawable = resources.getDrawable(when(weather) {
            "Clear" -> R.drawable.sunny
            "Snow" -> R.drawable.snow
            "Clouds" -> R.drawable.clouds
            else -> R.drawable.default_photo
        })

        backgroundPicture.setImageDrawable(drawable)
    }
}