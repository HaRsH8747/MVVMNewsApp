package com.example.mvvmnewsapp.ui.models

import com.example.mvvmnewsapp.ui.models.Article

data class NewsResponse(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)