package com.apolloagriculture.views.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apolloagriculture.views.theme.ApolloAgricultureTheme

@Composable
fun DayAfterTomorrowCard() {
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
            Spacer(Modifier.size(15.dp))
            Spacer(Modifier.size(15.dp))
        }
    }
}

@Preview
@Composable
fun DayAfterTomorrowCardPreview() {
    ApolloAgricultureTheme {
        DayAfterTomorrowCard()
    }
}

@Preview
@Composable
fun DayAfterTomorrowCardModePreview() {
    ApolloAgricultureTheme(darkTheme = true) {
        DayAfterTomorrowCard()
    }
}