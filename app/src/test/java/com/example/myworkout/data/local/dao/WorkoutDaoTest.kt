package com.example.myworkout.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myworkout.data.local.AppDatabase
import com.example.myworkout.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WorkoutDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var workoutDao: WorkoutDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        workoutDao = database.workoutDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertAndGetWorkout() = runBlocking {
        val workout = WorkoutEntity(name = "Test Workout", date = 1234567890)
        val id = workoutDao.insertWorkout(workout)
        
        val loaded = workoutDao.getWorkoutById(id)
        assertNotNull(loaded)
        assertEquals(workout.name, loaded?.name)
        assertEquals(workout.date, loaded?.date)
    }

    @Test
    fun getAllWorkoutsOrderedByDate() = runBlocking {
        val workout1 = WorkoutEntity(name = "Workout 1", date = 1000)
        val workout2 = WorkoutEntity(name = "Workout 2", date = 2000)
        
        workoutDao.insertWorkout(workout1)
        workoutDao.insertWorkout(workout2)
        
        val workouts = workoutDao.getAllWorkouts().first()
        assertEquals(2, workouts.size)
        assertEquals(workout2.name, workouts[0].name) // Newest first
    }
} 