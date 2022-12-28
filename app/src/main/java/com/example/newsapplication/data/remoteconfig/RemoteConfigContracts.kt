package com.example.newsapplication.data.remoteconfig

interface RemoteConfigContracts {

    fun getBaseUrl() : String

    fun getApiKey() : String

    fun getBreakingNewsUrl() : String

    fun getSearchNewsUrl() : String
}
