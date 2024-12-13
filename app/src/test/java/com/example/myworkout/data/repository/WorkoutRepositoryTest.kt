package com.example.myworkout.data.repository

import com.example.myworkout.data.local.dao.WorkoutDao
import com.example.myworkout.data.local.entity.WorkoutEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.*

class WorkoutRepositoryTest {
    private lateinit var workoutDao: WorkoutDao
    private lateinit var repository: WorkoutRepository

    @Before
    fun setup() {
        workoutDao = mock(WorkoutDao::class.java)
        repository = WorkoutRepository(workoutDao)
    }

    @Test
    fun `getAllWorkouts returns flow from dao`() = runBlocking {
        val workouts = listOf(
            WorkoutEntity(id = 1, name = "Workout 1", date = 1000),
            WorkoutEntity(id = 2, name = "Workout 2", date = 2000)
        )
        `when`(workoutDao.getAllWorkouts()).thenReturn(flowOf(workouts))

        val result = repository.getAllWorkouts().first()
        assertEquals(workouts, result)
    }

    @Test
    fun `getWorkoutById returns workout from dao`() = runBlocking {
        val workout = WorkoutEntity(id = 1, name = "Test Workout", date = 1000)
        `when`(workoutDao.getWorkoutById(1)).thenReturn(workout)

        val result = repository.getWorkoutById(1)
        assertEquals(workout, result)
    }
} 