package com.example.myworkout.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Training : Screen("training", "Training", Icons.Default.SportsGymnastics)
    object Nutrition : Screen("nutrition", "Ernährung", Icons.Default.Restaurant)

    companion object {
        val items = listOf(Home, Training, Nutrition)
    }
} 