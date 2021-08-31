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