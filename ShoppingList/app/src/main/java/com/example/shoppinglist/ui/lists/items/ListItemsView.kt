package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.ui.theme.ShoppingListTheme


@Composable
fun ListItemsView(
    modifier: Modifier = Modifier,
    listId: String,
    navController: NavHostController
) {
    val viewModel: ListItemsViewModel = viewModel()
    val state = viewModel.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Lista de itens
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(items = state.items) { _, item ->
                ItemRomView(item = item) {
                    viewModel.toggleItemChecked(listId = listId, item = item)
                }
            }
        }
        // Área dos botões
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão para adicionar
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onClick = { navController.navigate("add_item/$listId") },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Image(
                    modifier = Modifier
                        .scale(1.2f)
                        .size(32.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    painter = painterResource(R.drawable.add_item),
                    contentDescription = "Adicionar"
                )
            }
            // Botão para eliminar
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onClick = { navController.navigate("remove_item/$listId") },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Image(
                    modifier = Modifier
                        .scale(1.2f)
                        .size(32.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    painter = painterResource(R.drawable.remove_item),
                    contentDescription = "Excluir"
                )
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getItems(listId)
    }
}


@Preview(showBackground = true)
@Composable
fun ListItemsViewPreview(){
    ShoppingListTheme {
        ListItemsView(listId = "", navController = rememberNavController())
    }
}



