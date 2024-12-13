package com.example.myworkout.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myworkout.ui.viewmodel.NutritionViewModel

@Composable
fun NutritionStatsScreen(
    navController: NavController,
    viewModel: NutritionViewModel = hiltViewModel()
) {
    val stats by viewModel.stats.collectAsState()
    val selectedTimeRange by viewModel.selectedTimeRange.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, "Zurück")
            }
            Text(
                text = "Ernährungsstatistik",
                style = MaterialTheme.typography.headlineSmall
            )
            // Placeholder für Symmetrie
            Spacer(modifier = Modifier.width(48.dp))
        }

        // Zeitraumauswahl
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeRangeButton(
                text = "Woche",
                selected = selectedTimeRange == NutritionViewModel.TimeRange.WEEK,
                onClick = { viewModel.setTimeRange(NutritionViewModel.TimeRange.WEEK) }
            )
            TimeRangeButton(
                text = "Monat",
                selected = selectedTimeRange == NutritionViewModel.TimeRange.MONTH,
                onClick = { viewModel.setTimeRange(NutritionViewModel.TimeRange.MONTH) }
            )
            TimeRangeButton(
                text = "Jahr",
                selected = selectedTimeRange == NutritionViewModel.TimeRange.YEAR,
                onClick = { viewModel.setTimeRange(NutritionViewModel.TimeRange.YEAR) }
            )
        }

        // Rest der UI bleibt gleich, nur weeklyStats durch stats ersetzen
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Wochenübersicht
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = when (selectedTimeRange) {
                                NutritionViewModel.TimeRange.WEEK -> "Wochenübersicht"
                                NutritionViewModel.TimeRange.MONTH -> "Monatsübersicht"
                                NutritionViewModel.TimeRange.YEAR -> "Jahresübersicht"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        
                        // Gesamtstatistik der Woche
                        val totalCalories = stats.sumOf { it.totalCalories }
                        val avgProtein = stats.map { it.avgProtein }.average()
                        val totalMeals = stats.sumOf { it.mealCount }
                        
                        Text("Gesamtkalorien: $totalCalories kcal")
                        Text("Durchschnittliches Protein: ${"%.1f".format(avgProtein)}g pro Mahlzeit")
                        Text("Anzahl Mahlzeiten: $totalMeals")
                    }
                }
            }

            // Tägliche Statistiken
            items(stats) { stats ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = stats.day,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text("Kalorien: ${stats.totalCalories} kcal")
                        Text("Protein Ø: ${"%.1f".format(stats.avgProtein)}g")
                        Text("Kohlenhydrate Ø: ${"%.1f".format(stats.avgCarbs)}g")
                        Text("Fett Ø: ${"%.1f".format(stats.avgFat)}g")
                        Text("Mahlzeiten: ${stats.mealCount}")
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeRangeButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Text(text)
    }
}