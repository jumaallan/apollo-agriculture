/*
 * Copyright 2021 Apollo Agriculture
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apolloagriculture.data.repository

import com.apolloagriculture.data.model.Weather
import com.apolloagriculture.network.network.ApolloAgricultureResult
import kotlinx.coroutines.flow.Flow
import com.apolloagriculture.data.database.entity.Weather as WeatherModel

internal interface WeatherRepository {

    suspend fun fetchCurrentWeather(): ApolloAgricultureResult<Weather>
    suspend fun fetchOfflineCurrentWeather(): Flow<List<WeatherModel>>
    suspend fun saveCurrentWeather(weather: List<WeatherModel>)
}