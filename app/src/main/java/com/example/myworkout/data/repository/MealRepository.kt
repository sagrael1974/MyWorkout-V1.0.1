package com.example.myworkout.data.repository

import com.example.myworkout.data.local.dao.MealDao
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.Meal
import com.example.myworkout.data.mapper.toMeal
import com.example.myworkout.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
} 