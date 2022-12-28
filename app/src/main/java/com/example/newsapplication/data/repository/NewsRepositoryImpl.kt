package com.example.newsapplication.data.repository

import com.example.newsapplication.data.remoteconfig.ConfigManager
import com.example.newsapplication.data.source.network.NewsAPI
import com.example.newsapplication.db.ArticleDatabase
import com.example.newsapplication.models.Article
import com.example.newsapplication.models.NewsResponse
import com.example.newsapplication.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI,
    val db: ArticleDatabase,
    val configManager: ConfigManager
) : NewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        val url = configManager.getBreakingNewsUrl()
        return newsAPI.getBreakingNews(url,countryCode,pageNumber)
    }

    override suspend fun searchBreakingNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        val url = configManager.getSearchNewsUrl()
        return newsAPI.searchForNews(url,searchQuery,pageNumber)
    }

    override suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    override fun getSavedNews() = db.getArticleDao().getAllArticles()

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}