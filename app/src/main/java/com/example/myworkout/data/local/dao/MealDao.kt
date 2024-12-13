package com.example.myworkout.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.myworkout.data.local.entity.MealEntity

@Dao
interface MealDao {
    @Query("SELECT * FROM meals ORDER BY date DESC")
    fun getAllMeals(): Flow<List<MealEntity>>
    
    @Insert
    suspend fun insertMeal(meal: MealEntity)
    
    @Delete
    suspend fun deleteMeal(meal: MealEntity)
} 