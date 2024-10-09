package com.example.topnews.ui

import android.icu.text.Transliterator.Position
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.topnews.Models.Article
import com.example.topnews.R

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
            )
        } ?: run {
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                painter = painterResource(id = R.mipmap.images),
                contentDescription = "image article"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = article.title!!, style = MaterialTheme.typography.titleMedium)
            Text(text = article.description!!, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date", style = MaterialTheme.typography.bodySmall)
        }
    }
}
