package com.example.mvvmnewsapp.ui.repository

import com.example.mvvmnewsapp.ui.api.RetrofitInstance
import com.example.mvvmnewsapp.ui.db.ArticleDatabase

class NewsRepository(
        val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
            RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}