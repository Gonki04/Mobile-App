package com.example.shoppinglist.ui.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddListView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val viewModel : AddListViewModel = viewModel()
    val state = viewModel.state.value

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        // Título da página
        Text(
            text = "Add list",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Descrição
        Text(
            text = "Name of the list",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de texto para o nome da lista
        TextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("List name") },
            placeholder = { Text("Name of the list") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botão de adicionar
        Button(
            onClick = {
                viewModel.addList()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            )
        ) {
            Text(text = "Create list")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddListViewPreview(){
    ShoppingListTheme {
        AddListView()
    }
}