package com.example.myworkout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myworkout.R
import com.example.myworkout.ui.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToWorkoutDetail: (Long) -> Unit,
    navigateToBodyWorkout: () -> Unit,
    navigateToCardioWorkout: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Discover",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Settings, 
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, "Discover") },
                    label = { Text("Discover") },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.DarkGray
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, "My Workouts") },
                    label = { Text("My Workouts") },
                    selected = false,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.DarkGray
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.DarkGray
                    )
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Featured Cards
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeaturedCard(
                        modifier = Modifier.weight(1f),
                        title = "Strength",
                        onClick = { navigateToBodyWorkout() }
                    )
                    FeaturedCard(
                        modifier = Modifier.weight(1f),
                        title = "Cardio",
                        onClick = { navigateToCardioWorkout() }
                    )
                }
            }

            // Featured Workout Cards
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Test Workout",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "...",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }

            // Second Featured Workout Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Test Workout 2",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "...",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }

            // Third Featured Workout Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Test Workout 3",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "...",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturedCard(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val imageId = when(title) {
        "Strength" -> R.drawable.strength_background
        "Cardio" -> R.drawable.cardio_background
        else -> R.drawable.strength_background
    }
    android.util.Log.d("FeaturedCard", "Loading image with id: $imageId for title: $title")
    
    Surface(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            // Hintergrundbild
            Image(
                painter = painterResource(
                    id = imageId
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // Dunkler Overlay für bessere Lesbarkeit
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = "NEW",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
} 