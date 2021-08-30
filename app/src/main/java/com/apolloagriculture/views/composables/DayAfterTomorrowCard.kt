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