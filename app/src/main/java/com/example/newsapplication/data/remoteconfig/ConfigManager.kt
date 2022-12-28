package com.example.newsapplication.data.remoteconfig

import android.net.Uri
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class ConfigManager @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val gson: Gson,
) : RemoteConfigContracts {

    companion object {

        const val KEY_BASE_URL = "base_url"
        const val KEY_API_AUTH_KEY = "api_auth_key"
        const val KEY_BREAKING_NEWS_URL = "breaking_news_url"
        const val KEY_SEARCH_NEWS_URL = "search_news_url"



    }

    suspend fun suspendFetch(): Boolean {
        return suspendCoroutine { continuation ->
            firebaseRemoteConfig.fetchAndActivate()
                .addOnSuccessListener {
                    continuation.resume(true)
                }.addOnFailureListener {
                    continuation.resume(false)
                }
        }
    }

    override fun getBaseUrl(): String {
        return firebaseRemoteConfig.getString(KEY_BASE_URL)/*.let { url ->
            if(url.isEmpty()) "https://newsapi.org"
            else url
        }*/
    }

    override fun getApiKey(): String {
        return firebaseRemoteConfig.getString(KEY_API_AUTH_KEY)
    }

    override fun getBreakingNewsUrl(): String {
        return getApiUrlWithKeyFromConfig(KEY_BREAKING_NEWS_URL)
    }

    override fun getSearchNewsUrl(): String {
        return getApiUrlWithKeyFromConfig(KEY_SEARCH_NEWS_URL)
    }

    fun getApiUrlWithKeyFromConfig(urlKey: String): String {
        val apiKey = getApiKey()
        val uri = Uri.parse(firebaseRemoteConfig.getString(urlKey))
            .buildUpon().appendQueryParameter("apiKey",apiKey)
        return uri.toString()
    }
}