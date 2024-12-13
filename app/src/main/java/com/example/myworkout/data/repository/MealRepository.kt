package com.example.myworkout.data.repository

import com.example.myworkout.data.local.dao.MealDao
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.Meal
import com.example.myworkout.data.model.DailyStats
import com.example.myworkout.data.mapper.toMeal
import com.example.myworkout.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealDao: MealDao
) {
    fun getAllMeals(): Flow<List<Meal>> {
        return mealDao.getAllMeals().map { entities ->
            entities.map { entity -> entity.toMeal() }
        }
    }

    suspend fun addMeal(meal: Meal) {
        mealDao.insertMeal(meal.toEntity())
    }

    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal.toEntity())
    }

    suspend fun deleteOldMeals(olderThan: LocalDateTime) {
        val timestamp = olderThan.atZone(ZoneId.systemDefault()).toEpochSecond()
        mealDao.deleteOldMeals(timestamp)
    }

    fun getMealsByDateRange(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Meal>> {
        return mealDao.getMealsByDateRange(
            startTime = startDate.atZone(ZoneId.systemDefault()).toEpochSecond(),
            endTime = endDate.atZone(ZoneId.systemDefault()).toEpochSecond()
        ).map { entities ->
            entities.map { entity -> entity.toMeal() }
        }
    }

    fun getDailyStats(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<DailyStats>> {
        return mealDao.getDailyStats(
            startTime = startDate.atZone(ZoneId.systemDefault()).toEpochSecond(),
            endTime = endDate.atZone(ZoneId.systemDefault()).toEpochSecond()
        )
    }
} 