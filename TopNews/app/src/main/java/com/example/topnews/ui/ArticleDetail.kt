package com.example.topnews.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.topnews.Models.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetail(modifier: Modifier = Modifier, url: String, title: String) {
    val viewModel: FavoriteViewModel = viewModel()
    val isFavorite = rememberSaveable { mutableStateOf(viewModel.isFavorite(url)) }

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            AndroidView(
                modifier = Modifier.padding(top = 56.dp).fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.setSupportZoom(true)
                    }
                },
                update = { webView ->
                    webView.loadUrl(url)
                }
            )
        }
    }
}

@Preview
@Composable
fun ArticleDetailPreview() {
    ArticleDetail(modifier = Modifier, url = "https://www.google.com", title = "Google")
}