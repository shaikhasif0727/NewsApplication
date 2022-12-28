package com.example.newsapplication.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    val gson: Gson,
    ) {

    private val userInfoKey = stringPreferencesKey("user")

    suspend fun setUserInfo(userInfo : String){
        dataStore.edit { pref ->
            pref[userInfoKey] = userInfo
        }
    }

    fun getUserInfo() : Flow<String?> {
       return dataStore.data.map { it[userInfoKey] }
    }


}