package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.shoppinglist.models.Item
import kotlin.text.toDoubleOrNull

@Composable
fun AddItemView(
    listId: String,
    navController: NavHostController,
    viewModel: ListItemsViewModel
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") }
        )
        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Quantity") }
        )
        Button(onClick = {
            val newItem = Item(
                name = itemName,
                qtd = itemQuantity.toDoubleOrNull() ?: 0.0, // Handle invalid input
                checked = false
            )
            viewModel.addItem(listId, newItem)
            navController.popBackStack() // Go back to ListItemsView
        }) {
            Text("Save Item")
        }
    }
}