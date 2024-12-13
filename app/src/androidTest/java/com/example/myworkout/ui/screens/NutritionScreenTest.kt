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
class NutritionScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testAddMealFlow() {
        // Arrange
        composeTestRule.setContent {
            NutritionScreen(
                navController = rememberNavController()
            )
        }
        
        // Act
        composeTestRule.onNodeWithContentDescription("Mahlzeit hinzufügen").performClick()
        
        // Dialog ausfüllen
        composeTestRule.onNodeWithText("Name").performTextInput("Testmahlzeit")
        composeTestRule.onNodeWithText("Kalorien").performTextInput("500")
        composeTestRule.onNodeWithText("Protein (g)").performTextInput("30")
        composeTestRule.onNodeWithText("Kohlenhydrate (g)").performTextInput("60")
        composeTestRule.onNodeWithText("Fett (g)").performTextInput("20")
        
        composeTestRule.onNodeWithText("Hinzufügen").performClick()
        
        // Assert
        composeTestRule.onNodeWithText("Testmahlzeit").assertExists()
        composeTestRule.onNodeWithText("500 kcal").assertExists()
    }
} 