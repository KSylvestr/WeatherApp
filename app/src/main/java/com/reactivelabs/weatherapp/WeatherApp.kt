package com.reactivelabs.weatherapp

import android.app.Application
import androidx.room.Room
import com.reactivelabs.weatherapp.data.WeatherDatabase

class WeatherApp : Application() {
    companion object {
        lateinit var db: WeatherDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java, "weather-database"
        ).build()
    }
}