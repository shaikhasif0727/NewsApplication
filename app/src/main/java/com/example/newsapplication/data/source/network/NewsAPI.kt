package com.example.newsapplication.data.source.network


import com.example.newsapplication.models.Article
import com.example.newsapplication.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsAPI {


    //    @GET("v2/top-headlines")
    @GET
    suspend fun getBreakingNews(
        @Url
        url: String,
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
    ): Response<NewsResponse>

    //    @GET("v2/everything")
    @GET
    suspend fun searchForNews(
        @Url
        url: String,
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
    ): Response<NewsResponse>

}