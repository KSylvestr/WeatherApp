package com.reactivelabs.weatherapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "265c7fb04308fa7e744f9ee8aa2220bd"
    }

    @GET("weather?APPID=$API_KEY&units=metric")
    fun getCurrentWeatherForCity(@Query("q") cityAndCountryCode: String): Call<Weather>
}