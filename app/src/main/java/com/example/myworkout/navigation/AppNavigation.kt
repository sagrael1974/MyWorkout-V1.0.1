package com.example.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myworkout.ui.components.BottomNavigationBar

// Screen Imports
import com.example.myworkout.ui.screens.HomeScreen
import com.example.myworkout.ui.screens.WorkoutScreen
import com.example.myworkout.ui.screens.NutritionScreen
import com.example.myworkout.ui.screens.TrainingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HOME
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(navController)
        }
        composable(NavRoutes.WORKOUT) {
            WorkoutScreen(navController)
        }
        composable(
            route = "${NavRoutes.TRAINING}/{workoutId}",
            arguments = listOf(
                navArgument("workoutId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            TrainingScreen(
                workoutId = backStackEntry.arguments?.getString("workoutId"),
                navController = navController
            )
        }
        composable(NavRoutes.NUTRITION) {
            NutritionScreen(navController)
        }
    }

    BottomNavigationBar(navController)
} 