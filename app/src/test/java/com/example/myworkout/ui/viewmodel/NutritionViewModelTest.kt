package com.example.myworkout.ui.viewmodel

import com.example.myworkout.CoroutineTestRule
import com.example.myworkout.data.model.DailyStats
import com.example.myworkout.data.repository.MealRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class NutritionViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var repository: MealRepository
    private lateinit var viewModel: NutritionViewModel

    @Before
    fun setup() {
        repository = mockk {
            coEvery { getAllMeals() } returns flowOf(emptyList())
        }
        viewModel = NutritionViewModel(repository)
    }

    @Test
    fun `when time range changes, stats are updated`() = runTest {
        // Arrange
        val stats = DailyStats(
            day = LocalDate.now().toString(),
            totalCalories = 2000,
            avgProtein = 150f,
            avgCarbs = 200f,
            avgFat = 70f,
            mealCount = 3
        )
        coEvery { 
            repository.getDailyStats(any(), any())
        } returns flowOf(listOf(stats))

        // Act
        viewModel.setTimeRange(NutritionViewModel.TimeRange.MONTH)
        advanceUntilIdle()

        // Assert
        assertEquals(NutritionViewModel.TimeRange.MONTH, viewModel.selectedTimeRange.value)
        assertEquals(listOf(stats), viewModel.stats.first())
        
        coVerify { 
            repository.getDailyStats(
                startDate = any(),
                endDate = any()
            )
        }
    }
} 