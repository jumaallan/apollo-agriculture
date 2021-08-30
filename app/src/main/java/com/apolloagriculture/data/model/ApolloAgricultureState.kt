package com.apolloagriculture.data.model

internal sealed class ApolloAgricultureState {
    /**
     * Used to represent Loading status of an IO operation
     */
    object Loading : ApolloAgricultureState()

    /**
     * This is used to represent successful results
     * In this case, the Result has the ExchangeRate data class
     */
    data class Result(val weather: Weather) : ApolloAgricultureState()

    /**
     * Used to represent errors that resulted from an IO operation
     */
    data class Error(val message: String) : ApolloAgricultureState()
}