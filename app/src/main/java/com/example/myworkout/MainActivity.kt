package com.example.myworkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myworkout.navigation.NavRoutes
import com.example.myworkout.ui.screens.HomeScreen
import com.example.myworkout.ui.screens.NutritionScreen
import com.example.myworkout.ui.screens.TrainingScreen
import com.example.myworkout.ui.theme.MyWorkoutTheme
import com.example.myworkout.ui.components.BottomNavigation
import androidx.compose.material3.Scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWorkoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    Scaffold(
                        bottomBar = { BottomNavigation(navController = navController) }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = NavRoutes.HOME,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(NavRoutes.HOME) {
                                HomeScreen()
                            }
                            
                            composable(NavRoutes.TRAINING) {
                                TrainingScreen(
                                    workoutId = null,
                                    navController = navController
                                )
                            }
                            
                            composable(
                                route = NavRoutes.TRAINING_DETAIL,
                                arguments = listOf(
                                    navArgument("workoutId") { type = NavType.StringType }
                                )
                            ) { backStackEntry ->
                                TrainingScreen(
                                    workoutId = backStackEntry.arguments?.getString("workoutId"),
                                    navController = navController
                                )
                            }
                            
                            composable(NavRoutes.NUTRITION) {
                                NutritionScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
