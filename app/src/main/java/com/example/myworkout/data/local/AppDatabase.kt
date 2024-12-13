package com.example.myworkout.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.WorkoutEntity
import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.local.dao.MealDao
import com.example.myworkout.data.local.converter.DateTimeConverter

@Database(
    entities = [
        WorkoutEntity::class,
        MealEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun mealDao(): MealDao
} 