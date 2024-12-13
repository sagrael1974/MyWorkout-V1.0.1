package com.example.myworkout.data.repository

import com.example.myworkout.CoroutineTestRule
import com.example.myworkout.data.local.dao.MealDao
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.DailyStats
import com.example.myworkout.data.model.Meal
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId

class MealRepositoryTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var mealDao: MealDao
    private lateinit var repository: MealRepository

    @Before
    fun setup() {
        mealDao = mockk(relaxed = true)
        repository = MealRepository(mealDao)
    }

    @Test
    fun `getDailyStats returns correct data`() = runTest {
        // Arrange
        val today = LocalDateTime.now()
        val stats = DailyStats(
            day = today.toString(),
            totalCalories = 1300,
            avgProtein = 30f,
            avgCarbs = 75f,
            avgFat = 20f,
            mealCount = 2
        )
        
        coEvery { 
            mealDao.getDailyStats(any(), any()) 
        } returns flowOf(listOf(stats))

        // Act
        val result = repository.getDailyStats(today, today).first()

        // Assert
        assertEquals(1300, result[0].totalCalories)
        assertEquals(30f, result[0].avgProtein)
        assertEquals(2, result[0].mealCount)
    }

    @Test
    fun `addMeal converts and saves meal correctly`() = runTest {
        // Arrange
        val meal = Meal(
            name = "Test Meal",
            calories = 500,
            protein = 30f,
            carbs = 60f,
            fat = 20f,
            date = LocalDateTime.now()
        )
        
        // Act
        repository.addMeal(meal)
        
        // Assert
        coVerify { 
            mealDao.insertMeal(match { 
                it.name == meal.name &&
                it.calories == meal.calories &&
                it.protein == meal.protein &&
                it.carbs == meal.carbs &&
                it.fat == meal.fat
            })
        }
    }

    @Test
    fun `deleteMeal removes correct meal`() = runTest {
        // Arrange
        val meal = Meal(
            id = 1,
            name = "Test Meal",
            calories = 500,
            protein = 30f,
            carbs = 60f,
            fat = 20f,
            date = LocalDateTime.now()
        )
        
        // Act
        repository.deleteMeal(meal)
        
        // Assert
        coVerify { 
            mealDao.deleteMeal(match { 
                it.id == meal.id 
            })
        }
    }

    @Test
    fun `getMealsByDateRange returns meals in correct range`() = runTest {
        // Arrange
        val startDate = LocalDateTime.now().minusDays(1)
        val endDate = LocalDateTime.now()
        val meals = listOf(
            MealEntity(
                id = 1,
                name = "Test Meal 1",
                calories = 500,
                protein = 30f,
                carbs = 60f,
                fat = 20f,
                date = startDate.atZone(ZoneId.systemDefault()).toEpochSecond()
            )
        )
        
        coEvery { 
            mealDao.getMealsByDateRange(any(), any()) 
        } returns flowOf(meals)

        // Act
        val result = repository.getMealsByDateRange(startDate, endDate).first()

        // Assert
        assertEquals(1, result.size)
        assertEquals("Test Meal 1", result[0].name)
        assertEquals(500, result[0].calories)
    }
} 