package com.example.myworkout.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NutritionStatsScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testTimeRangeSelection() {
        // Arrange
        composeTestRule.setContent {
            NutritionStatsScreen(
                navController = rememberNavController()
            )
        }
        
        // Act & Assert
        composeTestRule.onNodeWithText("Woche").assertIsSelected()
        
        composeTestRule.onNodeWithText("Monat").performClick()
        composeTestRule.onNodeWithText("Monatsübersicht").assertExists()
        
        composeTestRule.onNodeWithText("Jahr").performClick()
        composeTestRule.onNodeWithText("Jahresübersicht").assertExists()
    }
} 