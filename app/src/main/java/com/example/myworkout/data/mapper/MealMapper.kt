package com.example.myworkout.data.mapper

import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.Meal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun MealEntity.toMeal(): Meal = Meal(
    id = id,
    name = name,
    calories = calories,
    protein = protein,
    carbs = carbs,
    fat = fat,
    date = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(date),
        ZoneId.systemDefault()
    )
)

fun Meal.toEntity(): MealEntity = MealEntity(
    id = id,
    name = name,
    calories = calories,
    protein = protein,
    carbs = carbs,
    fat = fat,
    date = date.atZone(ZoneId.systemDefault()).toEpochSecond()
) 