package com.example.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myworkout.ui.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Training.route) {
            TrainingScreen(workoutId = null, navController)
        }
        composable(Screen.Nutrition.route) {
            NutritionScreen(navController)
        }
        composable(Screen.NutritionStats.route) {
            NutritionStatsScreen(navController)
        }
        composable(Screen.WorkoutDetail.route) { backStackEntry ->
            TrainingScreen(
                workoutId = backStackEntry.arguments?.getString("workoutId"),
                navController = navController
            )
        }
    }
} 