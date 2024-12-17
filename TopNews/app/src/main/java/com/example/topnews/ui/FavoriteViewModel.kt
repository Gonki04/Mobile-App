package com.example.topnews.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.topnews.Models.Article

class FavoriteViewModel : ViewModel() {
    private val _favorites = mutableStateListOf<Article>()
    val favorites: List<Article> get() = _favorites

    fun addFavorite(article: Article) {
        if (!_favorites.contains(article)) {
            _favorites.add(article)
        }
    }

    fun removeFavorite(url: String) {
        _favorites.removeAll { it.url == url }
    }

    fun isFavorite(url: String): Boolean {
        return _favorites.any { it.url == url }
    }
}