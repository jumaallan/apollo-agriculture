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

import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.apolloagriculture.views.theme.ApolloAgricultureTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTodayCardIsDisplayed() {

        // Start the app
        composeTestRule.setContent {
            ApolloAgricultureTheme {
                Surface {
                    Scaffold(
                        content = {
                            ShowWeatherScreen()
                        }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Today").assertIsDisplayed()
        composeTestRule.onAllNodes(hasText("Low Temperature")).assertAny(isEnabled())
        composeTestRule.onAllNodes(hasText("High Temperature")).assertAny(isEnabled())
    }

    @Test
    fun testTomorrowCardIsDisplayed() {

        // Start the app
        composeTestRule.setContent {
            ApolloAgricultureTheme {
                Surface {
                    Scaffold(
                        content = {
                            ShowWeatherScreen()
                        }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Tomorrow").assertIsDisplayed()
        composeTestRule.onAllNodes(hasText("Low Temperature")).assertAny(isEnabled())
        composeTestRule.onAllNodes(hasText("High Temperature")).assertAny(isEnabled())
    }

    @Test
    fun testDayAfterTomorrowCardIsDisplayed() {

        // Start the app
        composeTestRule.setContent {
            ApolloAgricultureTheme {
                Surface {
                    Scaffold(
                        content = {
                            ShowWeatherScreen()
                        }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Day After Tomorrow").assertIsDisplayed()
        composeTestRule.onAllNodes(hasText("Low Temperature")).assertAny(isEnabled())
        composeTestRule.onAllNodes(hasText("High Temperature")).assertAny(isEnabled())
    }
}