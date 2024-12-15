package com.example.myworkout.data.local.dao

import androidx.room.*
import com.example.myworkout.data.local.entity.WorkoutEntity
import com.example.myworkout.data.local.model.CategoryCount
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workouts WHERE category = :category")
    fun getWorkoutsByCategory(category: String): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutById(id: Long): Flow<WorkoutEntity?>

    @Query("""
        SELECT * FROM workouts
        GROUP BY category
    """)
    @MapInfo(keyColumn = "category", valueColumn = "")
    fun getWorkoutsPerCategory(): Flow<Map<String, List<WorkoutEntity>>>

    @Query("""
        SELECT category, COUNT(*) as count 
        FROM workouts 
        GROUP BY category
    """)
    fun getWorkoutCountByCategory(): Flow<List<CategoryCount>>
} 