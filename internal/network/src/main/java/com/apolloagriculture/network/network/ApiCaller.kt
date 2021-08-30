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
package com.apolloagriculture.network.network

import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ApolloAgricultureResult<T> = withContext(dispatcher) {
    try {
        ApolloAgricultureResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Timber.e(throwable)
        when (throwable) {
            is IOException -> ApolloAgricultureResult.ApolloAgricultureError
            is HttpException -> {
                val code = throwable.code()

                val errorResponse = convertErrorBody(throwable)
                ApolloAgricultureResult.ServerError(code, errorResponse)
            }
            else -> {
                ApolloAgricultureResult.ServerError(null, null)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? = try {
    throwable.response()?.errorBody()?.charStream()?.let {
        val gson = GsonBuilder()
            .create()
        gson.fromJson(it, ErrorResponse::class.java)
    }
} catch (exception: Exception) {
    Timber.e(exception)
    null
}