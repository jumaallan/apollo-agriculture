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
import com.apolloagriculture.data.repository.WeatherRepository

internal class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

//    private val mutableApolloAgricultureState: MutableStateFlow<ApolloAgricultureState> =
//        MutableStateFlow(ApolloAgricultureState.Loading)
//    val weatherState = mutableApolloAgricultureState.asStateFlow()
//
//    fun fetchCurrentExchangeRate() = viewModelScope.launch {
//        when (
//            val result =
//                weatherRepository.fetchCurrentWeather()
//        ) {
//            ApolloAgricultureResult.ApolloAgricultureError -> {
//                mutableApolloAgricultureState.value =
//                    ApolloAgricultureState.Error("Some error occurred when fetching weather updates")
//            }
//            is ApolloAgricultureResult.ServerError -> {
//                mutableApolloAgricultureState.value =
//                    ApolloAgricultureState.Error(result.errorBody?.message.toString())
//            }
//            is ApolloAgricultureResult.Success -> {
//                mutableApolloAgricultureState.value = ApolloAgricultureState.Result(result.data)
//            }
//        }
//    }
}