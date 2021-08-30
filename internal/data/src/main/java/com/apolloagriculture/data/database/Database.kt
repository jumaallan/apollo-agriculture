package com.apolloagriculture.data.database

import androidx.room.RoomDatabase
import com.apolloagriculture.data.database.dao.WeatherDao
import com.apolloagriculture.data.database.entity.Weather

@androidx.room.Database(
    entities = [
        Weather::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}