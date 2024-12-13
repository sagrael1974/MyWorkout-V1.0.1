package com.example.myworkout.data.model

import java.time.LocalDateTime

data class Meal(
    val id: Long = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val date: LocalDateTime = LocalDateTime.now()
) 