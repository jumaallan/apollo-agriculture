package com.apolloagriculture.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.apolloagriculture.data.database.entity.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao : BaseDao<Weather> {

    @Query("SELECT * FROM Weather")
    fun fetchCurrentWeather(): Flow<List<Weather>>
}