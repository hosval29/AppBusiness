package com.example.appbusiness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appbusiness.presentation.business.detail.BusinessDetailScreen
import com.example.appbusiness.presentation.business.main.BusinessMainScreen
import com.example.appbusiness.ui.theme.AppBusinessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBusinessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        scaffoldState = scaffoldState
                    ) {
                        NavHost(navController = navController, startDestination = "main") {
                            composable("main") {
                                BusinessMainScreen(
                                    navController = navController
                                )
                            }
                            composable("detail") {
                                BusinessDetailScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
