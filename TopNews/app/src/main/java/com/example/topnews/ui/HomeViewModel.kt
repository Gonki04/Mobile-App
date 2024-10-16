package com.example.topnews.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.topnews.Models.Article
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class HomeViewModel: ViewModel() {
    var articles = mutableStateOf(listOf<Article>())
        private set
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf<String?>(null)

    fun fetchArticles() {
        isLoading.value = true

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d1f150e77bb041b7b7076fa26475260d")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                isLoading.value = true
                error.value = e.message
            }

            override fun onResponse(call: Call, response: Response) {
                isLoading.value = true
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        for (index in 0 until articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            val article = Article.fromJson(articleJson)
                            articlesResult.add(article)
                        }
                    }
                    articles.value = articlesResult
                }
            }
        })
    }
}