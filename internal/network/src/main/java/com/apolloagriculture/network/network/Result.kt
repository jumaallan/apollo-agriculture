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

sealed class Result<out T> {

    /**
     * This is used to represent successful results
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Used to represent errors that resulted from an IO operation
     */
    data class Error(
        val errorMessage: String? = null,
        val exception: Throwable? = null
    ) : Result<Nothing>()

    /**
     * Used to represent Loading status of an IO operation
     */
    object Loading : Result<Nothing>()
}