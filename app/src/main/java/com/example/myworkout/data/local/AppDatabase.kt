package com.example.myworkout.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
} 