package com.apolloagriculture.data.repository

import com.apolloagriculture.data.database.dao.WeatherDao
import com.apolloagriculture.data.model.Weather
import com.apolloagriculture.network.data.api.WeatherAPI
import com.apolloagriculture.network.network.ApolloAgricultureResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow

internal class WeatherRepositoryImpl(
    private val weatherAPI: WeatherAPI,
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {

    override suspend fun fetchCurrentWeather(): ApolloAgricultureResult<Weather> {
        return emptyFlow()
    }

}