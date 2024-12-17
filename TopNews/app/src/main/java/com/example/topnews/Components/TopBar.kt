package com.example.topnews.Components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.example.topnews.Models.Article
import com.example.topnews.Screen
import com.example.topnews.ui.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    showFavoriteIcon: Boolean = false,
    articleUrl: String? = null,
    articleTitle: String? = null,
    viewModel: FavoriteViewModel
) {
    val isFavorite = rememberSaveable { mutableStateOf(articleUrl?.let { viewModel.isFavorite(it) } ?: false) }

    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.background,
        ),
        title = {
            Text(
                text = "Top News",
                color = MaterialTheme.colorScheme.background
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        },
        actions = {
            IconButton(onClick = { /* Action for share icon */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
            if (showFavoriteIcon && articleUrl != null && articleTitle != null) {
                IconButton(onClick = {
                    isFavorite.value = !isFavorite.value
                    if (isFavorite.value) {
                        viewModel.addFavorite(Article(title = articleTitle, url = articleUrl))
                    } else {
                        viewModel.removeFavorite(articleUrl)
                    }
                }) {
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        },
    )
}