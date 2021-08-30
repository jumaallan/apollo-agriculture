package com.apolloagriculture.data.model

internal data class Weather(
    val lowTemp: Double,
    val highTemp: Double,
    val icon: String,
    val description: String
)