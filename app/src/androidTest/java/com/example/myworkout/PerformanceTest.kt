package com.example.myworkout

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import com.example.myworkout.navigation.AppNavigation
import com.example.myworkout.ui.theme.MyWorkoutTheme
import kotlin.system.measureTimeMillis

@HiltAndroidTest
class PerformanceTest : BaseInstrumentedTest() {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testNavigationPerformance() = runTest {
        // Given
        composeRule.setContent {
            MyWorkoutTheme {
                AppNavigation()
            }
        }

        // When & Then
        val navigationTime = measureTimeMillis {
            // Simulate navigation actions
            composeRule.onNodeWithText("Statistics").performClick()
            composeRule.onNodeWithText("Settings").performClick()
            composeRule.onNodeWithText("Home").performClick()
        }

        // Assert navigation time is within acceptable range
        assert(navigationTime < 1000) { "Navigation took too long: $navigationTime ms" }
    }

    @Test
    fun testScreenLoadPerformance() = runTest {
        composeRule.setContent {
            MyWorkoutTheme {
                AppNavigation()
            }
        }

        // Measure initial screen load time
        val loadTime = measureTimeMillis {
            composeRule.waitForIdle()
        }

        // Assert load time is within acceptable range
        assert(loadTime < 500) { "Screen load took too long: $loadTime ms" }
    }

    // Weitere Performance-Tests...
} 
