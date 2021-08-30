package com.apolloagriculture.views.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apolloagriculture.data.model.ApolloAgricultureState
import com.apolloagriculture.data.repository.WeatherRepository
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

    fun fetchCurrentExchangeRate() = viewModelScope.launch {
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
