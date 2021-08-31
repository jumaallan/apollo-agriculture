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

import app.cash.turbine.test
import com.apolloagriculture.data.repository.WeatherRepository
import com.apolloagriculture.data.sample.testWeatherResponse
import com.apolloagriculture.data.sample.testWeatherResponseResult
import com.apolloagriculture.network.network.ApolloAgricultureResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import kotlin.time.ExperimentalTime

/**
 * To run tests in Android Studio you need to install Spek Framework plugin (search for Spek Framework).
 *
 * The select, "Run Spek" option
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
internal class WeatherViewModelTest : Spek({

    val weatherRepository = mockk<WeatherRepository>()
    lateinit var weatherViewModel: WeatherViewModel

    val dispatcher = TestCoroutineDispatcher()

    beforeGroup {
        Dispatchers.setMain(dispatcher = dispatcher)
    }

    group("Fetching Weather Information") {

        beforeEachTest {
            weatherViewModel =
                WeatherViewModel(weatherRepository = weatherRepository)
        }

        test("Assert that an event was received and return it") {

            runBlocking {
                coEvery { weatherRepository.fetchCurrentWeather() } returns ApolloAgricultureResult.Success(
                    data = testWeatherResponse
                )
                weatherViewModel.fetchCurrentWeather()
                coVerify { weatherRepository.fetchCurrentWeather() }
                weatherViewModel.weatherState.test {
                    awaitEvent()
                }
            }
        }

        test("Test that weather information is fetched successfully") {

            runBlocking {
                coEvery { weatherRepository.fetchCurrentWeather() } returns ApolloAgricultureResult.Success(
                    data = testWeatherResponse
                )
                weatherViewModel.fetchCurrentWeather()
                coVerify { weatherRepository.fetchCurrentWeather() }
                weatherViewModel.weatherState.test {
                    Assertions.assertEquals(awaitItem(), testWeatherResponseResult)
                }
            }
        }
    }

    afterGroup {
        Dispatchers.resetMain()
    }
})