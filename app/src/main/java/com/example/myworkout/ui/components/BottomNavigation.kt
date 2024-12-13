package com.example.myworkout.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myworkout.navigation.NavRoutes

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == NavRoutes.HOME,
            onClick = {
                navController.navigate(NavRoutes.HOME) {
                    popUpTo(NavRoutes.HOME) { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Training") },
            label = { Text("Training") },
            selected = currentRoute?.startsWith(NavRoutes.TRAINING) == true,
            onClick = {
                navController.navigate("${NavRoutes.TRAINING}/null") {
                    popUpTo(NavRoutes.HOME)
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Ernährung") },
            label = { Text("Ernährung") },
            selected = currentRoute == NavRoutes.NUTRITION,
            onClick = {
                navController.navigate(NavRoutes.NUTRITION) {
                    popUpTo(NavRoutes.HOME)
                }
            }
        )
    }
} 