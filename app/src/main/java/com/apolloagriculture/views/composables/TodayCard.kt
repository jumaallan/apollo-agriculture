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
package com.apolloagriculture.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apolloagriculture.R
import com.apolloagriculture.data.model.ApolloAgricultureState
import com.apolloagriculture.network.data.models.IconType
import com.apolloagriculture.views.theme.ApolloAgricultureTheme
import com.apolloagriculture.views.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TodayCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Spacer(Modifier.size(6.dp))
            Today()
            Spacer(Modifier.size(4.dp))
        }
    }
}

@Composable
fun Today() {
    val isLightTheme =
        MaterialTheme.colors.isLight // check whether the device’s theme selected is Light mode or not
    val weatherViewModel: WeatherViewModel = getViewModel()
    weatherViewModel.fetchCurrentWeather()
    val weatherState: ApolloAgricultureState by weatherViewModel.weatherState.collectAsState(initial = ApolloAgricultureState.Loading)

    var isLoading by remember { mutableStateOf(true) }

    when (weatherState) {
        is ApolloAgricultureState.Error -> {
            isLoading = false
        }
        ApolloAgricultureState.Loading -> {
            isLoading = true
        }
        is ApolloAgricultureState.Result -> {
            isLoading = false
            weatherViewModel.setWeather((weatherState as ApolloAgricultureState.Result).weather)
        }
    }

    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Today",
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center
                    )
                    WeatherIconImage(
                        image =
                        if (isLightTheme) {
                            when (weatherViewModel.weather.value["today"]?.icon) {
                                IconType.CLEAR_DAY -> {
                                    R.drawable.ic_weather_clear_day
                                }
                                IconType.BROKEN_OVERCAST_CLOUDS_DAY -> {
                                    R.drawable.ic_weather_one_cloud
                                }
                                IconType.SCATTERED_CLOUDS_DAY -> {
                                    R.drawable.ic_weather_some_clouds
                                }
                                else -> {
                                    R.drawable.ic_weather_clear_day
                                }
                            }
                        } else {
                            when (weatherViewModel.weather.value["today"]?.icon) {
                                IconType.CLEAR_DAY -> {
                                    R.drawable.ic_weather_clear_day
                                }
                                IconType.BROKEN_OVERCAST_CLOUDS_DAY -> {
                                    R.drawable.ic_weather_one_cloud
                                }
                                IconType.SCATTERED_CLOUDS_DAY -> {
                                    R.drawable.ic_weather_some_clouds
                                }
                                else -> {
                                    R.drawable.ic_weather_clear_day
                                }
                            }
                        },
                        contentScale = ContentScale.Fit,
                        contentDesc = "Weather Icon",
                        modifier = Modifier
                            .size(44.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${weatherViewModel.weather.value["today"]?.lowTemp}",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Text(
                            text = "Low Temperature",
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${weatherViewModel.weather.value["today"]?.highTemp}",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Text(
                            text = "High Temperature",
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "${weatherViewModel.weather.value["today"]?.description}",
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun TodayCardPreview() {
    ApolloAgricultureTheme {
        TodayCard()
    }
}

@Preview
@Composable
fun TodayCardDarkDarkModePreview() {
    ApolloAgricultureTheme(darkTheme = true) {
        TodayCard()
    }
}