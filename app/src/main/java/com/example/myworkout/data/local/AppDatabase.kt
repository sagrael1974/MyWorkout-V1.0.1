package com.example.myworkout.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myworkout.data.local.dao.MealDao
import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.local.entity.WorkoutEntity
import com.example.myworkout.data.local.converter.DateConverter

@Database(
    entities = [
        MealEntity::class,
        WorkoutEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun workoutDao(): WorkoutDao
} 