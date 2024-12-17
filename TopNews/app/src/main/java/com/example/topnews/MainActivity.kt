package com.example.topnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.topnews.Components.BottomBar
import com.example.topnews.Components.TopBar
import com.example.topnews.theme.TopNewsTheme
import com.example.topnews.ui.ArticleDetail
import com.example.topnews.ui.FavoriteView
import com.example.topnews.ui.FavoriteViewModel
import com.example.topnews.ui.HomeView

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopNewsTheme {
                val navController = rememberNavController()
                val viewModel: FavoriteViewModel = FavoriteViewModel()
                val currentBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry.value?.destination?.route
                val showFavoriteIcon = remember { mutableStateOf(false) }
                val articleUrl = remember { mutableStateOf<String?>(null) }
                val articleTitle = remember { mutableStateOf<String?>(null) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            navController = navController,
                            showFavoriteIcon = showFavoriteIcon.value,
                            articleUrl = articleUrl.value,
                            articleTitle = articleTitle.value,
                            viewModel = viewModel
                        )
                    },
                    bottomBar = { BottomBar(navController = navController) },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            showFavoriteIcon.value = false
                            HomeView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = Screen.ArticleDetail.route) {
                            val url = it.arguments?.getString("articleUrl")
                            showFavoriteIcon.value = true
                            articleUrl.value = url
                            articleTitle.value = "Article"
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: "",
                                title = "Article"
                            )
                        }
                        composable(route = Screen.Favorite.route) {
                            showFavoriteIcon.value = false
                            FavoriteView()
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ArticleDetail : Screen("article_detail/{articleUrl}")
    object Favorite : Screen("favorite")
}