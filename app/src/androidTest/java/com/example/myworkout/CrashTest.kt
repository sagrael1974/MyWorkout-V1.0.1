package com.example.myworkout

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule

import com.example.myworkout.domain.repository.WorkoutRepository
import com.example.myworkout.domain.model.Workout
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class CrashTest : BaseInstrumentedTest() {
    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Inject
    lateinit var repository: WorkoutRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInvalidWorkoutData() = runTest {
        // Given
        val invalidWorkout = Workout(
            id = 1L,
            name = "",  // Invalid empty name
            category = "Test",
            description = "Test Description",
            date = System.currentTimeMillis(),
            duration = -1,  // Invalid negative duration
            caloriesBurned = 0,
            type = "CARDIO"
        )

        // When
        repository.insertWorkout(invalidWorkout)
        testScheduler.advanceUntilIdle()

        // Then
        composeRule.onNodeWithText("Invalid workout data").assertExists()
    }

    @Test
    fun testConcurrentModification() = runTest {
        // Given
        val workout = Workout(
            id = 1L,
            name = "Test Workout",
            category = "Test",
            description = "Test Description",
            date = System.currentTimeMillis(),
            duration = 30,
            caloriesBurned = 300,
            type = "CARDIO"
        )
        repository.insertWorkout(workout)
        testScheduler.advanceUntilIdle()

        // When - Simulate concurrent modifications
        coroutineScope {
            launch {
                repository.updateWorkout(workout.copy(name = "Modified 1"))
            }
            launch {
                repository.updateWorkout(workout.copy(name = "Modified 2"))
            }
        }
        testScheduler.advanceUntilIdle()

        // Then
        composeRule.onNodeWithText("Concurrent modification error").assertExists()
    }

    @Test
    fun testNavigationCrash() = runTest {
        composeRule.setContent {
            MainActivity()
        }
        testScheduler.advanceUntilIdle()

        // Attempt to navigate to an invalid route
        composeRule.onNodeWithContentDescription("Invalid Route").performClick()
        testScheduler.advanceUntilIdle()

        // Verify error handling
        composeRule.onNodeWithText("Navigation error").assertExists()
    }
} 