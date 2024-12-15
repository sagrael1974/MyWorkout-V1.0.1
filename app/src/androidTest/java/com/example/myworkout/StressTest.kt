package com.example.myworkout

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import com.example.myworkout.navigation.AppNavigation
import com.example.myworkout.ui.theme.MyWorkoutTheme
import kotlinx.coroutines.delay

@HiltAndroidTest
class StressTest : BaseInstrumentedTest() {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testRapidNavigationStress() = runTest {
        // Given
        composeRule.setContent {
            MyWorkoutTheme {
                AppNavigation()
            }
        }

        // When - Schnelle Navigation zwischen Screens
        repeat(50) {
            composeRule.onNodeWithText("Statistics").performClick()
            composeRule.onNodeWithText("Settings").performClick()
            composeRule.onNodeWithText("Home").performClick()
            delay(50) // Kurze Verzögerung zwischen Navigationen
        }

        // Then - Überprüfe, ob die App stabil bleibt
        composeRule.onRoot().printToLog("Navigation Stress Test")
        composeRule.onNodeWithText("Home").assertExists()
    }

    @Test
    fun testConcurrentOperationsStress() = runTest {
        composeRule.setContent {
            MyWorkoutTheme {
                AppNavigation()
            }
        }

        // Simuliere mehrere gleichzeitige Operationen
        repeat(20) {
            composeRule.onNodeWithText("Add Workout").performClick()
            composeRule.onNodeWithText("Cancel").performClick()
            composeRule.onNodeWithText("Statistics").performClick()
            composeRule.onNodeWithText("Home").performClick()
        }

        // Überprüfe die Stabilität
        composeRule.onNodeWithText("Home").assertExists()
    }

    @Test
    fun testLongRunningOperationStress() = runTest {
        composeRule.setContent {
            MyWorkoutTheme {
                AppNavigation()
            }
        }

        // Simuliere lang laufende Operation
        repeat(10) {
            // Navigiere durch alle Hauptscreens
            composeRule.onNodeWithText("Statistics").performClick()
            delay(100)
            composeRule.onNodeWithText("Settings").performClick()
            delay(100)
            composeRule.onNodeWithText("Home").performClick()
            delay(100)
        }

        // Überprüfe die App-Stabilität
        composeRule.onRoot().printToLog("Long Running Operation Test")
        composeRule.onNodeWithText("Home").assertExists()
    }
} 