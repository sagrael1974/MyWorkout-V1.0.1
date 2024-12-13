package com.example.myworkout.data.local.dao

import androidx.room.*
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.DailyStats
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meals ORDER BY date DESC")
    fun getAllMeals(): Flow<List<MealEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)
    
    @Delete
    suspend fun deleteMeal(meal: MealEntity)
    
    @Query("DELETE FROM meals WHERE date < :timestamp")
    suspend fun deleteOldMeals(timestamp: Long)
    
    @Query("SELECT * FROM meals WHERE date BETWEEN :startTime AND :endTime ORDER BY date DESC")
    fun getMealsByDateRange(startTime: Long, endTime: Long): Flow<List<MealEntity>>
    
    @Query("""
        SELECT 
            strftime('%Y-%m-%d', datetime(date, 'unixepoch')) as day,
            SUM(calories) as totalCalories,
            AVG(protein) as avgProtein,
            AVG(carbs) as avgCarbs,
            AVG(fat) as avgFat,
            COUNT(*) as mealCount
        FROM meals 
        WHERE date BETWEEN :startTime AND :endTime
        GROUP BY day
        ORDER BY day DESC
    """)
    fun getDailyStats(startTime: Long, endTime: Long): Flow<List<DailyStats>>
} 