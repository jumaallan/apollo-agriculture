package com.apolloagriculture.network.data.api

import com.apolloagriculture.network.data.models.WeatherResponse
import retrofit2.http.GET

interface WeatherAPI {

    @GET("/android-takehomeassignment/weather.json")
    suspend fun fetchCurrentWeather(): WeatherResponse
}