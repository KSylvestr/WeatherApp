package com.reactivelabs.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Weather(
    val id: Int,
    val name: String,
    val weather: List<WeatherData>,
    val main: WeatherMain
)

data class WeatherData(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Entity
data class WeatherMain(
    @PrimaryKey val id: Int = currentId,
    val temp: Double,
    val pressure: Int,
    val humidity: Int
) {
    companion object {
        var currentId = 1
    }

    init {
        currentId++
    }
}