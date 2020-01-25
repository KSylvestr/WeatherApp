package com.reactivelabs.weatherapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert
    fun insertAllWeatherMain(vararg weatherMains: WeatherMain)

    @Query("SELECT * FROM weathermain")
    fun getAllWeatherMain(): List<WeatherMain>
}