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

import com.apolloagriculture.dispatcher.MockRequestDispatcher
import com.apolloagriculture.network.data.api.WeatherAPI
import com.apolloagriculture.network.data.models.WeatherResponse
import com.apolloagriculture.network.network.ApolloAgricultureResult
import com.google.common.truth.Truth
import com.google.gson.GsonBuilder
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * To run tests in Android Studio you need to install Spek Framework plugin (search for Spek Framework).
 *
 * The select, "Run Spek" option
 */
internal class WeatherRepositoryImplTest : Spek({

    // Mock Web Server and Network API
    lateinit var mockWebServer: MockWebServer
    lateinit var okHttpClient: OkHttpClient
    lateinit var loggingInterceptor: HttpLoggingInterceptor
    var weatherAPI: WeatherAPI

    lateinit var weatherRepository: WeatherRepository

    lateinit var result: ApolloAgricultureResult<HashMap<String, WeatherResponse>>
    lateinit var weather: com.apolloagriculture.data.database.entity.Weather

    fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    Feature("Fetching Weather Reports from API") {

        beforeEachScenario {

            mockWebServer = MockWebServer()
            mockWebServer.dispatcher = MockRequestDispatcher()
            mockWebServer.start()
            loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClient = buildOkhttpClient(loggingInterceptor)

            val gson = GsonBuilder()
                .serializeNulls()
                .create()

            weatherAPI = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WeatherAPI::class.java)

            weatherRepository =
                WeatherRepositoryImpl(weatherAPI = weatherAPI, weatherDao = mockk())
        }

        afterEachScenario {
            mockWebServer.shutdown()
        }

        Scenario("Fetching the weather information from the Apollo API") {

            Given("Make the actual API call to get the result") {
                runBlocking {
                    result = weatherRepository.fetchCurrentWeather()
                }
            }

            When("We assert that the result we get is an instance of ApolloAgricultureResult") {
                Truth.assertThat(result).isInstanceOf(ApolloAgricultureResult.Success::class.java)
            }

            Then("We check the value of today's weather to check if we get the correct lowTemp value") {
                Truth.assertThat((result as ApolloAgricultureResult.Success).data["today"]?.lowTemp)
                    .isEqualTo(23.36)
            }

            Then("We check the value of tomorrow's weather to check if we get the correct highTemp value") {
                Truth.assertThat((result as ApolloAgricultureResult.Success).data["tomorrow"]?.highTemp)
                    .isEqualTo(24.9)
            }

            Then("We check the value of day after tomorrow weather to check if we get the correct description value") {
                Truth.assertThat((result as ApolloAgricultureResult.Success).data["dayAfterTomorrow"]?.description)
                    .isEqualTo("broken clouds")
            }
        }
    }
})