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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apolloagriculture.R
import com.apolloagriculture.views.theme.ApolloAgricultureTheme

@Composable
fun WeatherIconImage(
    image: Int,
    contentScale: ContentScale,
    contentDesc: String,
    modifier: Modifier
) {
    val paintImage: Painter = painterResource(id = image)
    Image(
        painter = paintImage,
        contentDescription = contentDesc,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Preview
@Composable
fun WeatherIconImagePreview() {
    ApolloAgricultureTheme {
        WeatherIconImage(
            image = R.drawable.ic_weather_snow,
            contentScale = ContentScale.Fit,
            contentDesc = "Weather img",
            modifier = Modifier
                .size(250.dp)
                .padding(top = 40.dp, bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun WeatherIconImageDarkModePreview() {
    ApolloAgricultureTheme(darkTheme = true) {
        WeatherIconImage(
            image = R.drawable.ic_weather_rain,
            contentScale = ContentScale.Fit,
            contentDesc = "Weather img",
            modifier = Modifier
                .size(250.dp)
                .padding(top = 40.dp, bottom = 20.dp)
        )
    }
}