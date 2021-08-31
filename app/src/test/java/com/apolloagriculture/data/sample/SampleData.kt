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
package com.apolloagriculture.data.sample

import com.apolloagriculture.data.database.entity.Weather
import com.apolloagriculture.data.model.ApolloAgricultureState
import com.apolloagriculture.network.data.models.WeatherResponse

internal val testWeatherData = listOf(
    Weather(
        id = 0,
        day = "today",
        lowTemp = 23.36,
        highTemp = 24.66,
        icon = "CLEAR_DAY",
        description = "clear sky"
    ),
    Weather(
        id = 0,
        day = "tomorrow",
        lowTemp = 23.63,
        highTemp = 24.9,
        icon = "SCATTERED_CLOUDS_DAY",
        description = "scattered clouds"
    ),
    Weather(
        id = 0,
        day = "dayAfterTomorrow",
        lowTemp = 22.73,
        highTemp = 25.14,
        icon = "BROKEN_OVERCAST_CLOUDS_DAY",
        description = "broken clouds"
    )
)

internal val day = WeatherResponse(
    lowTemp = 22.73,
    highTemp = 25.14,
    icon = "BROKEN_OVERCAST_CLOUDS_DAY",
    description = "broken clouds"
)

internal val testWeatherResponse = hashMapOf(
    "today" to day
)

internal val testWeatherResponseResult = ApolloAgricultureState.Result(testWeatherResponse)