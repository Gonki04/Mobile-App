package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
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
    listId : String,
    navController: NavHostController
){

    val viewModel : ListItemsViewModel = viewModel()
    val state = viewModel.state.value


    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd) {

        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(
                items = state.items
            ) { index, item ->

                ItemRomView(item = item) {
                    viewModel
                        .toggleItemChecked(
                            listId = listId,
                            item = item
                        )
                }
            }
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp),
            onClick = {navController.navigate("add_item/$listId")}) {
            Image(
                modifier = Modifier
                    .scale(2.0f)
                    .size(64.dp),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(R.drawable.add_item),
                contentDescription = "add"
            )
        }
    }
    LaunchedEffect (key1 = true){
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



