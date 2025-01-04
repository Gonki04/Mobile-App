package com.example.topnews.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topnews.ui.Favorites.ArticlesState
import com.example.topnews.Models.Article
import com.example.topnews.Repositories.ArticleRepository
import com.example.topnews.Repositories.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class ArticlesState (
    val articles: List<Article> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
@HiltViewModel
class HomeViewModel @Inject constructor(
    val articleRepository: ArticleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState : StateFlow<ArticlesState> = _uiState.asStateFlow()
    fun fetchArticles() {
        articleRepository
            .fetchArticles("top-headlines?country=us")
            .onEach { result ->
                when(result) {
                    is ResultWrapper.Success ->{
                        _uiState.value = ArticlesState(
                            articles = result.data?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is ResultWrapper.Error ->{
                        _uiState.value = ArticlesState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is ResultWrapper.Loading ->{
                        _uiState.value = ArticlesState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}