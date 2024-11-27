package com.example.shoppinglist.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun ListListsView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {

    val viewModel: ListListsViewModel = viewModel()
    val state = viewModel.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {

        // Lista de Itens com Card
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(items = state.listItemsList) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(
                                Screen.ListItems.route.replace("{listId}", item.docId!!)
                            )
                        }
                        .background(
                            color = Color.White,
                            shape = MaterialTheme.shapes.medium
                        )
                        .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.name ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }
        }

        // Botão de Adicionar Lista
        Button(
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp),
            onClick = { navController.navigate(Screen.AddList.route) },
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            )
        ) {
            Image(
                modifier = Modifier
                    .scale(1.5f)
                    .size(40.dp),
                painter = painterResource(R.drawable.add_new_list),
                contentDescription = "Add new list",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getLists()
    }
}

@Preview(showBackground = true)
@Composable
fun ListListViewPreview(){
    ShoppingListTheme {
        ListListsView()
    }
}