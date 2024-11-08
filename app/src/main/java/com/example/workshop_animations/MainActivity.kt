package com.example.workshop_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workshop_animations.some_feature.FeatureEntry
import com.example.workshop_animations.ui.theme.Workshop_animationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Workshop_animationsTheme {
                Box(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "some_feature"
                    ) {
                        composable(route = "some_feature") {
                            FeatureEntry(navController)
                        }

                        composable(route = "another_feature") {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = Color.Red)
                            )
                        }
                    }
                }
            }
        }
    }
}
