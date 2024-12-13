package com.example.myworkout.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myworkout.navigation.Screen
import com.example.myworkout.data.model.Meal
import com.example.myworkout.ui.dialog.AddMealDialog
import com.example.myworkout.ui.viewmodel.NutritionViewModel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun NutritionScreen(
    navController: NavController,
    viewModel: NutritionViewModel = hiltViewModel()
) {
    var showAddDialog by remember { mutableStateOf(false) }
    val meals by viewModel.meals.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ernährungsmanagement",
                style = MaterialTheme.typography.headlineMedium
            )
            IconButton(
                onClick = { navController.navigate(Screen.NutritionStats.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = "Statistiken anzeigen"
                )
            }
        }
        
        val todayMeals by viewModel.getMealsByDateRange(
            startDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS),
            endDate = LocalDateTime.now()
        ).collectAsState(initial = emptyList())
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Heute",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Kalorien: ${todayMeals.sumOf { it.calories }} kcal",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(meals) { meal ->
                MealItem(
                    meal = meal,
                    onDelete = { viewModel.deleteMeal(meal) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Mahlzeit hinzufügen")
        }
    }

    if (showAddDialog) {
        AddMealDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { meal ->
                viewModel.addMeal(meal)
            }
        )
    }
}

@Composable
private fun MealItem(
    meal: Meal,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Mahlzeit löschen"
                    )
                }
            }
            Text(
                text = "${meal.calories} kcal",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("P: ${meal.protein}g")
                Text("K: ${meal.carbs}g")
                Text("F: ${meal.fat}g")
            }
        }
    }
}