package com.example.myworkout.data.local.entity

import org.junit.Test
import org.junit.Assert.*

class WorkoutEntityTest {
    
    @Test
    fun `create workout entity with default id`() {
        val workout = WorkoutEntity(
            name = "Test Workout",
            date = System.currentTimeMillis()
        )
        
        assertEquals(0, workout.id)
        assertEquals("Test Workout", workout.name)
        assertTrue(workout.date > 0)
    }
    
    @Test
    fun `create workout entity with custom id`() {
        val workout = WorkoutEntity(
            id = 1,
            name = "Test Workout",
            date = 1234567890
        )
        
        assertEquals(1, workout.id)
        assertEquals("Test Workout", workout.name)
        assertEquals(1234567890, workout.date)
    }
} 