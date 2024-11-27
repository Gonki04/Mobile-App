package com.example.shoppinglist.ui.lists.items

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RemoveItemView(
    listId: String,
    navController: NavHostController,
    viewModel: ListItemsViewModel
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        // Título da página
        Text(
            text = "Remover Item",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Descrição
        Text(
            text = "Enter the item name and quantity to remove.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de texto para o nome do item
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Campo de texto para a quantidade
        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Quantity") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botão de remover
        Button(
            onClick = {
                viewModel.deleteItemByNameAndQtd(listId, itemName, itemQuantity.toInt())
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Remove Item")
        }
    }
}
