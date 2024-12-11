package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.auth
import com.example.shoppinglist.ui.lists.AddListView
import com.example.shoppinglist.ui.lists.ListListsView
import com.example.shoppinglist.ui.lists.RemoveListView
import com.example.shoppinglist.ui.lists.items.AddItemView
import com.example.shoppinglist.ui.lists.items.ListItemsView
import com.example.shoppinglist.ui.lists.items.ListItemsViewModel
import com.example.shoppinglist.ui.lists.items.RemoveItemView
import com.example.shoppinglist.ui.login.LoginView
import com.example.shoppinglist.ui.login.RegisterView
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.google.firebase.Firebase

const val TAG = "ShoppingList"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Login.route
                    ){
                        composable(Screen.Login.route) {
                            LoginView(
                                modifier = Modifier.padding(innerPadding),
                                onLoginSuccess = {
                                    navController.navigate(Screen.Home.route)
                                },
                                navController = navController
                            )
                        }
                        composable(Screen.Register.route) {
                            RegisterView(
                                modifier = Modifier.padding(innerPadding),
                                onRegisterSucess = {
                                    navController.navigate(Screen.Home.route)
                                },
                                navController = navController
                            )
                        }
                        composable(Screen.Home.route) {
                            ListListsView(
                                navController = navController
                            )
                        }
                        composable (Screen.AddList.route){
                            AddListView(navController = navController)
                        }
                        composable(Screen.ListItems.route) {
                            ListItemsView(navController = navController, listId = it.arguments?.getString("listId") ?: "")
                        }
                        composable(Screen.AddItem.route) {
                            AddItemView(
                                listId = it.arguments?.getString("listId") ?: "",
                                navController = navController,
                                viewModel = ListItemsViewModel())
                        }
                        composable(Screen.RemoveItem.route) {
                            RemoveItemView(
                                listId = it.arguments?.getString("listId") ?: "",
                                navController = navController,
                                viewModel = ListItemsViewModel())
                        }
                        composable(Screen.RemoveList.route) {
                            RemoveListView(
                                navController = navController,
                                viewModel = viewModel()
                            )

                        }
                    }
                }
                LaunchedEffect(Unit) {
                    val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    if (currentUser != null){
                        navController.navigate(Screen.Home.route)
                    }
                }
            }
        }
    }
}

sealed class Screen (val route:String){
    object Login : Screen("login")
    object Home : Screen("home")
    object Register : Screen("register")
    object AddList : Screen("add_list")
    object AddItem : Screen("add_item/{listId}")
    object RemoveItem : Screen("remove_item/{listId}")
    object RemoveList : Screen("remove_list/{listId}")
    object ListItems : Screen("list_items/{listId}")
}