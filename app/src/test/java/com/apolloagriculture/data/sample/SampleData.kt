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
import com.apolloagriculture.network.data.models.IconType
import com.apolloagriculture.network.data.models.WeatherResponse

internal val day = WeatherResponse(
    lowTemp = 22.73,
    highTemp = 25.14,
    icon = IconType.BROKEN_OVERCAST_CLOUDS_DAY,
    description = "broken clouds"
)

internal val testWeatherResponse = hashMapOf(
    "today" to day
)

internal val testWeatherResponseResult = ApolloAgricultureState.Result(testWeatherResponse)