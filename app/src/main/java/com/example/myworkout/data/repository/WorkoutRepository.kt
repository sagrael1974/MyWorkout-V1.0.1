package com.example.myworkout.data.repository

import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    fun getAllWorkouts(): Flow<List<WorkoutEntity>> {
        return workoutDao.getAllWorkouts()
    }
    
    suspend fun getWorkoutById(id: Long): WorkoutEntity? {
        return workoutDao.getWorkoutById(id)
    }
    
    suspend fun insertWorkout(workout: WorkoutEntity): Long {
        return workoutDao.insertWorkout(workout)
    }
    
    suspend fun updateWorkout(workout: WorkoutEntity) {
        workoutDao.updateWorkout(workout)
    }
    
    suspend fun deleteWorkout(workout: WorkoutEntity) {
        workoutDao.deleteWorkout(workout)
    }
} 