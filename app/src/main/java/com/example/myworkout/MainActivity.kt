package com.example.myworkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myworkout.ui.theme.MyWorkoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWorkoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("MyWorkout App")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Willkommen bei $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyWorkoutTheme {
        Greeting("MyWorkout App")
    }
}
