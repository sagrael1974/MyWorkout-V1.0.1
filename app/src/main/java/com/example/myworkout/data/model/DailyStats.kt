package com.example.myworkout.data.model

data class DailyStats(
    val day: String,
    val totalCalories: Int,
    val avgProtein: Float,
    val avgCarbs: Float,
    val avgFat: Float,
    val mealCount: Int
) 