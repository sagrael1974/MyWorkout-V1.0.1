package com.example.myworkout.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myworkout.data.local.converter.DateConverter
import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.WorkoutEntity

@Database(
    entities = [
        WorkoutEntity::class
    ],
    version = 3,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}