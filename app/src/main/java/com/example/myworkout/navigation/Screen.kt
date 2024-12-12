package com.example.myworkout.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Nutrition : Screen("nutrition")
    object Training : Screen("training/{workoutId}") {
        fun createRoute(workoutId: String?) = "training/${workoutId ?: ""}"
    }
} 