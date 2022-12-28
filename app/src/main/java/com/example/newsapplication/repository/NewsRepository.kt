package com.example.newsapplication.repository

import androidx.lifecycle.LiveData
import com.example.newsapplication.models.Article
import com.example.newsapplication.models.NewsResponse
import retrofit2.Response

interface NewsRepository {

   suspend fun getBreakingNews(countryCode: String, pageNumber: Int) : Response<NewsResponse>

   suspend fun searchBreakingNews(searchQuery: String, pageNumber: Int) : Response<NewsResponse>

   suspend fun upsert(article: Article) : Long

   fun getSavedNews() : LiveData<List<Article>>

   suspend fun deleteArticle(article: Article) : Unit
}