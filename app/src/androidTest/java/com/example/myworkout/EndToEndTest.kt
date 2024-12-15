package com.example.myworkout

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.myworkout.domain.repository.WorkoutRepository
import com.example.myworkout.domain.model.Workout
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class EndToEndTest : BaseInstrumentedTest() {
    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Inject
    lateinit var repository: WorkoutRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testFullWorkoutFlow() = runTest {
        // Given - Erstelle einige Test-Workouts
        val workout1 = Workout(
            id = 1L,
            name = "Morning Run",
            category = "Cardio",
            description = "Easy morning jog",
            date = System.currentTimeMillis(),
            duration = 30,
            caloriesBurned = 300,
            type = "CARDIO"
        )

        val workout2 = Workout(
            id = 2L,
            name = "Weight Training",
            category = "Strength",
            description = "Upper body workout",
            date = System.currentTimeMillis(),
            duration = 45,
            caloriesBurned = 400,
            type = "STRENGTH"
        )

        val workout3 = Workout(
            id = 3L,
            name = "Yoga Session",
            category = "Flexibility",
            description = "Morning yoga routine",
            date = System.currentTimeMillis(),
            duration = 60,
            caloriesBurned = 200,
            type = "FLEXIBILITY"
        )

        // When - Füge Workouts hinzu
        repository.insertWorkout(workout1)
        repository.insertWorkout(workout2)
        repository.insertWorkout(workout3)

        // Setze den Hauptbildschirm
        composeRule.setContent {
            MainActivity()
        }

        // Then - Überprüfe die UI-Elemente
        composeRule.onNodeWithText("Morning Run").assertExists()
        composeRule.onNodeWithText("Weight Training").assertExists()
        composeRule.onNodeWithText("Yoga Session").assertExists()

        // Teste das Hinzufügen eines neuen Workouts
        composeRule.onNodeWithContentDescription("Add Workout").performClick()
        
        // Fülle das Formular aus
        composeRule.onNodeWithTag("workout_name").performTextInput("Evening Run")
        composeRule.onNodeWithTag("workout_category").performTextInput("Cardio")
        composeRule.onNodeWithTag("workout_description").performTextInput("Evening jogging session")
        composeRule.onNodeWithTag("workout_duration").performTextInput("30")
        composeRule.onNodeWithTag("workout_calories").performTextInput("300")
        composeRule.onNodeWithTag("workout_type").performTextInput("CARDIO")

        // Speichere das neue Workout
        composeRule.onNodeWithText("Save").performClick()

        // Überprüfe, ob das neue Workout angezeigt wird
        composeRule.onNodeWithText("Evening Run").assertExists()
    }
} 