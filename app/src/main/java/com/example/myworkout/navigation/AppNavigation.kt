package com.example.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Screen Imports
import com.example.myworkout.ui.screens.HomeScreen
import com.example.myworkout.ui.screens.NutritionScreen
import com.example.myworkout.ui.screens.TrainingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        
        composable(route = Screen.Nutrition.route) {
            NutritionScreen(navController = navController)
        }
        
        composable(
            route = Screen.Training.route,
            arguments = listOf(
                navArgument("workoutId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val workoutId = entry.arguments?.getString("workoutId")
            TrainingScreen(workoutId = workoutId, navController = navController)
        }
    }
} 