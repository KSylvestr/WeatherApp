package com.reactivelabs.weatherapp.data

import android.util.Log
import com.reactivelabs.weatherapp.WeatherApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class WeatherRepository(
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope {
    private val api = Retrofit.Builder()
        .baseUrl(WeatherApi.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getCurrentWeatherForCity(city: String, countryCode: String) = async {
        val weather = api.getCurrentWeatherForCity("$city,$countryCode")
            .execute()
            .body()
        WeatherApp.db
            .weatherMainDao()
            .insertAllWeatherMain(weather!!.main)
        Log.i("DbTest", WeatherApp.db
            .weatherMainDao()
            .getAllWeatherMain()
            .toString())
        weather
    }
}