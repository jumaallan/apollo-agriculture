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