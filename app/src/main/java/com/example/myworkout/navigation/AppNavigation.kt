package com.example.myworkout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myworkout.ui.screens.HomeScreen
import com.example.myworkout.ui.screens.TrainingScreen
import com.example.myworkout.ui.screens.WorkoutScreen
import com.example.myworkout.ui.screens.StatisticsScreen
import com.example.myworkout.ui.screens.BodyWorkoutScreen
import com.example.myworkout.ui.screens.CardioWorkoutScreen
import com.example.myworkout.ui.viewmodels.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                navigateToWorkoutDetail = { workoutId ->
                    navController.navigate("${Screen.WorkoutDetail.route}/$workoutId")
                },
                navigateToBodyWorkout = {
                    navController.navigate(Screen.BodyWorkout.route)
                },
                navigateToCardioWorkout = {
                    navController.navigate(Screen.CardioWorkout.route)
                }
            )
        }
        
        composable(Screen.Training.route) {
            TrainingScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Screen.WorkoutDetail.route}/{workoutId}",
            arguments = listOf(
                navArgument("workoutId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            WorkoutScreen(
                workoutId = backStackEntry.arguments?.getLong("workoutId") ?: -1L,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Statistics.route) {
            StatisticsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.BodyWorkout.route) {
            BodyWorkoutScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.CardioWorkout.route) {
            CardioWorkoutScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed interface Screen {
    val route: String

    data object Home : Screen {
        override val route = "home"
    }

    data object Training : Screen {
        override val route = "training"
    }

    data object WorkoutDetail : Screen {
        override val route = "workout"
    }

    data object Statistics : Screen {
        override val route = "statistics"
    }

    data object BodyWorkout : Screen {
        override val route = "body_workout"
    }

    data object CardioWorkout : Screen {
        override val route = "cardio_workout"
    }
} 