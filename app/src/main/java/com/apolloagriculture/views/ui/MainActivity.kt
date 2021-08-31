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
package com.apolloagriculture.views.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apolloagriculture.views.composables.DayAfterTomorrowCard
import com.apolloagriculture.views.composables.TodayCard
import com.apolloagriculture.views.composables.TomorrowCard
import com.apolloagriculture.views.theme.ApolloAgricultureTheme
import com.apolloagriculture.views.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApolloAgricultureTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        content = { ShowWeatherScreen() }
                    )
                }
            }
        }
    }
}

@Composable
fun ShowWeatherScreen() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        TodayCard()
        TomorrowCard()
        DayAfterTomorrowCard()
    }
}

@Preview(showBackground = true)
@Composable
fun ShowWeatherScreenPreview() {
    ApolloAgricultureTheme {
        ShowWeatherScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ShowWeatherScreenDarModePreview() {
    ApolloAgricultureTheme(darkTheme = true) {
        ShowWeatherScreen()
    }
}