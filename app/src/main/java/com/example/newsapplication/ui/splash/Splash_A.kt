package com.example.newsapplication.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsapplication.R
import com.example.newsapplication.ui.NewsActivity
import com.example.newsapplication.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Splash_A : AppCompatActivity() {

    private val viewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fetchConfigAndCheckExpToken()
    }


    private fun fetchConfigAndCheckExpToken() {
        lifecycleScope.launch {
            val configFetchDone = viewModel.fetchConfig()
            if(configFetchDone)
            {
                showToast("Should Active")
                startActivity(Intent(applicationContext,NewsActivity::class.java))
                finish()
            }
            else{
                showToast("Shouldn't Active")
            }
        }
    }
}