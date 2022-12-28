package com.example.newsapplication.ui.splash

import androidx.lifecycle.ViewModel
import com.example.newsapplication.data.remoteconfig.ConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val configManager: ConfigManager
) : ViewModel() {

    suspend fun fetchConfig(): Boolean {
        return configManager.suspendFetch()
    }
}