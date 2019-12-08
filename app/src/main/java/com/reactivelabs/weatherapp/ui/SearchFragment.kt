package com.reactivelabs.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
import com.reactivelabs.weatherapp.data.WeatherRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchFragment(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    companion object {
        const val CITY = "city"
        const val WEATHER_OVERALL = "overall"
        const val WEATHER_DESCRIPTION = "description"
        const val HUMIDITY = "humidity"
        const val TEMP = "temp"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = WeatherRepository()
        startSearch.setOnClickListener {
            launch {
                if (cityName.text.isNotBlank() && countryCode.text.isNotBlank()) {
                    startSearch.visibility = View.INVISIBLE
                    loader.visibility = View.VISIBLE
                    val cityName = cityName.text.toString()
                    val countryCode = countryCode.text.toString()
                    val weatherResponse = repository.getCurrentWeatherForCity(cityName, countryCode).await()
                    Log.i("SearchFragment", weatherResponse?.toString() ?: "Empty response")
                    weatherResponse?.apply {
                        val weatherFragment = WeatherFragment()
                        val data = Bundle().let {
                            it.putString(CITY, name)
                            it.putString(WEATHER_OVERALL, weather.first().main)
                            it.putString(WEATHER_DESCRIPTION, weather.first().description)
                            it.putDouble(TEMP, main.temp)
                            it.putInt(HUMIDITY, main.humidity)
                            it

                        }

                        weatherFragment.arguments = data

                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container, weatherFragment)
                            ?.addToBackStack(null)
                            ?.commit()
                    }

                    startSearch.visibility = View.VISIBLE
                    loader.visibility = View.INVISIBLE

                }
            }
        }
    }
}