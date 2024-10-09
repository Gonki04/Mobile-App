package com.example.topnews.ui

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.topnews.Models.Article
import com.example.topnews.R
import com.example.topnews.theme.TopNewsTheme
import java.util.Date

@Composable
fun HomeView(modifier: Modifier = Modifier) {

    var articles = arrayListOf(
        Article(
            "dsflsdlfk selkfwçlfmkqw",
            "Description",
            null,
            "url",
            Date()
        ),
        Article(
            "WEFWEFWEFWF ",
            "Description",
            null,
            "url",
            Date()
        ),
        Article(
            "daddwdad",
            "Description",
            null,
            "url",
            Date()
        )
    )
    LazyColumn {
        itemsIndexed(
            items = articles,
        ) { index, article ->
            //Text(text = article.title!!)
            //Text(text = article.description!!)
            RowArticle(modifier = Modifier, article = article)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    TopNewsTheme {
        HomeView()
    }
}