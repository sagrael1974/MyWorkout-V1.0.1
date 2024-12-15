package com.example.myworkout.data.mapper

import com.example.myworkout.data.local.entity.WorkoutEntity
import com.example.myworkout.domain.model.Workout
import com.example.myworkout.domain.model.WorkoutType

fun WorkoutEntity.toDomain(): Workout {
    return Workout(
        id = id.toLong(),
        name = name,
        description = description,
        duration = duration,
        caloriesBurned = caloriesBurned,
        type = WorkoutType.valueOf(type),
        category = category ?: "",
        date = date ?: System.currentTimeMillis()
    )
}

fun Workout.toEntity(): WorkoutEntity {
    return WorkoutEntity(
        id = id.toInt(),
        name = name,
        description = description,
        duration = duration,
        caloriesBurned = caloriesBurned,
        type = type.name,
        category = category,
        date = date
    )
} 