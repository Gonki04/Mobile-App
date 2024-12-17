package com.example.topnews.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.topnews.Models.Article

@Composable
fun FavoriteView() {
    val viewModel: FavoriteViewModel = viewModel()
    val favorites = viewModel.favorites

    Column {
        Text(text = "Favorites", textAlign = TextAlign.Center)
        LazyColumn {
            items(favorites) { article ->
                Text(text = article.title ?: "No Title")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteViewPreview() {
    FavoriteView()
}