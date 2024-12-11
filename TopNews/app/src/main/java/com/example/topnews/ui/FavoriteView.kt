package com.example.topnews.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteView() {
    Text(text = "Favorite", textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun FavoriteViewPreview() {
    FavoriteView()
}