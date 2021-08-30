package com.apolloagriculture.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val lowTemp: Double,
    val highTemp: Double,
    val icon: String,
    val description: String
)