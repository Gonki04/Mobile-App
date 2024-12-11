package com.example.topnews.Components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.topnews.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.background,
        ),
        title = {
            Text(
                text = "",
                color = MaterialTheme.colorScheme.background
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        },
        actions = {
            IconButton(onClick = { /* Action for icon button */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
        }
    )
}