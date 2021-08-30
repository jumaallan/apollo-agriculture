package com.apolloagriculture.network.network

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApolloAgricultureResult<out R> {

    /**
     * This is used to represent successful responses (2xx response codes, non empty response bodies)
     */
    data class Success<out T>(val data: T) : ApolloAgricultureResult<T>()

    /**
     * Used to represent Server errors (non 2xx status code)
     */
    data class ServerError(
        val code: Int? = null,
        val errorBody: ErrorResponse? = null
    ) : ApolloAgricultureResult<Nothing>()

    /**
     * Used to represent connectivity errors (a request that didn't result in a response)
     */
    object ApolloAgricultureError : ApolloAgricultureResult<Nothing>()
}