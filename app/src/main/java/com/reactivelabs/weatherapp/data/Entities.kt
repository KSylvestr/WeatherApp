package com.reactivelabs.weatherapp.data

data class Weather(
    val name: String,
    val weather: List<WeatherData>,
    val main: WeatherMain
)

data class WeatherData(
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherMain(
    val temp: Double,
    val pressure: Int,
    val humidity: Int
)