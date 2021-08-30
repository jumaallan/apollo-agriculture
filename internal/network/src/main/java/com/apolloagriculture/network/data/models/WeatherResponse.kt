package com.apolloagriculture.network.data.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("description") val description: String,
    @SerializedName("highTemp") val highTemp: Double,
    @SerializedName("icon") val icon: String,
    @SerializedName("lowTemp") val lowTemp: Double
)