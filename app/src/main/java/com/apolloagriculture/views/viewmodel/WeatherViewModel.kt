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
package com.apolloagriculture.views.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apolloagriculture.data.model.ApolloAgricultureState
import com.apolloagriculture.data.repository.WeatherRepository
import com.apolloagriculture.network.data.models.WeatherResponse
import com.apolloagriculture.network.network.ApolloAgricultureResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val mutableApolloAgricultureState: MutableStateFlow<ApolloAgricultureState> =
        MutableStateFlow(ApolloAgricultureState.Loading)
    val weatherState = mutableApolloAgricultureState.asStateFlow()

    private val _weather: MutableStateFlow<HashMap<String, WeatherResponse>> = MutableStateFlow(
        hashMapOf()
    )
    val weather = _weather.asStateFlow()

    fun setWeather(weather: HashMap<String, WeatherResponse>) {
        _weather.value = weather
    }

    fun fetchCurrentWeather() = viewModelScope.launch {
        when (
            val result =
                weatherRepository.fetchCurrentWeather()
        ) {
            ApolloAgricultureResult.ApolloAgricultureError -> {
                mutableApolloAgricultureState.value =
                    ApolloAgricultureState.Error("Some error occurred when fetching weather updates")
            }
            is ApolloAgricultureResult.ServerError -> {
                mutableApolloAgricultureState.value =
                    ApolloAgricultureState.Error(result.errorBody?.message.toString())
            }
            is ApolloAgricultureResult.Success -> {
                mutableApolloAgricultureState.value = ApolloAgricultureState.Result(result.data)
            }
        }
    }
}