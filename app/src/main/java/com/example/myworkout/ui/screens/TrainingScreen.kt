package com.example.myworkout.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TrainingScreen(
    workoutId: String?,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Training: $workoutId")
        
        Button(onClick = { navController.navigateUp() }) {
            Text("Zurück")
        }
    }
}