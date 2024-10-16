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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.topnews.Models.Article
import com.example.topnews.R
import com.example.topnews.theme.TopNewsTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Date

@Composable
fun HomeView( modifier: Modifier = Modifier) {

    val viewModel = HomeViewModel()
    val articles by viewModel.articles

    HomeViewContent(modifier = modifier,
        articles = articles)

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
}

@Composable
fun HomeViewContent(modifier: Modifier = Modifier,
                    articles: List<Article>) {

    if (articles.isEmpty()) {
        Box(modifier){
            Text("No articles found!")
        }
    }else{
        LazyColumn(modifier = modifier) {
            itemsIndexed(
                items = articles,
            ){ index, article ->
                RowArticle(article = article)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    /*
        val articles = arrayListOf(
            Article("Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lobortis augue in erat scelerisque, vitae fringilla nisi tempus. Sed finibus tellus porttitor dignissim eleifend. Etiam sed neque libero. Integer auctor turpis est. Nunc ac auctor velit. Nunc et mi sollicitudin, iaculis nunc et, congue neque. Suspendisse potenti. Vestibulum finibus justo sed eleifend commodo. Phasellus vestibulum ligula nisi, convallis rhoncus quam placerat id. Donec eu lobortis lacus, quis porta tortor. Suspendisse quis dolor sapien. Maecenas finibus purus at orci aliquam eleifend. Nam venenatis sapien ac enim efficitur pretium. Praesent sagittis risus vitae feugiat blandit. Etiam non neque arcu. Cras a mauris eu erat sodales iaculis non a lorem.",
                urlToImage = "https://media.istockphoto.com/id/1166633394/pt/foto/victorian-british-army-gymnastic-team-aldershot-19th-century.jpg?s=1024x1024&w=is&k=20&c=fIfqysdzOinu8hNJG6ZXOhl8ghQHA7ySl8BZZYWrxyQ="),
            Article("Lorem Ipsum is simply dummy text of the printing", "description"))
    */
    val articles = arrayListOf<Article>()
    TopNewsTheme {
        HomeViewContent(articles = articles)
    }
}