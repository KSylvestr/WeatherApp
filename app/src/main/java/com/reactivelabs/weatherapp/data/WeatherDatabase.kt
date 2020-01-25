package com.reactivelabs.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WeatherMain::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherMainDao(): WeatherDao
}