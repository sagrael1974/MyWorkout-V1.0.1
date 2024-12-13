package com.example.myworkout.data.mapper

import com.example.myworkout.data.local.entity.MealEntity
import com.example.myworkout.data.model.Meal

fun MealEntity.toMeal(): Meal {
    return Meal(
        id = id,
        name = name,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fat = fat,
        date = date
    )
}

fun Meal.toEntity(): MealEntity {
    return MealEntity(
        id = id,
        name = name,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fat = fat,
        date = date
    )
} 