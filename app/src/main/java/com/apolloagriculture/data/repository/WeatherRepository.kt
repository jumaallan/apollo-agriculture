package com.apolloagriculture.data.repository

import com.apolloagriculture.data.model.Weather
import com.apolloagriculture.network.network.ApolloAgricultureResult

internal interface WeatherRepository {

    suspend fun fetchCurrentWeather(): ApolloAgricultureResult<Weather>
}