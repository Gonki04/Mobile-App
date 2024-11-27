package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppinglist.R
import com.example.shoppinglist.models.Item

@Composable
fun AddItemView(
    listId: String,
    navController: NavHostController,
    viewModel: ListItemsViewModel
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    val isInputValid = itemName.isNotBlank() && itemQuantity.toIntOrNull() != null

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.add_item),
            contentDescription = "Add Item",
            modifier = Modifier.size(128.dp)
        )

        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            placeholder = { Text("Enter the item name") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Quantity") },
            placeholder = { Text("Enter the quantity") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        if (itemQuantity.toIntOrNull() == null && itemQuantity.isNotEmpty()) {
            Text(
                text = "Invalid quantity. Please enter a valid number.",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
            onClick = {
                if (isInputValid) {
                    val newItem = Item(name = itemName, qtd = itemQuantity.toInt(), checked = false)
                    viewModel.addItem(listId, newItem)
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            enabled = isInputValid
        ) {
            Text("Save Item")
        }

        if (isInputValid) {
            Text("Preview: $itemName - Quantity: $itemQuantity")
        }
    }
}
