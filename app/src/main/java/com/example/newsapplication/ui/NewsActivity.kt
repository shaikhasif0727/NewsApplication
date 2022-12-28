package com.example.newsapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.example.newsapplication.BuildConfig

import com.example.newsapplication.R
import com.example.newsapplication.data.remoteconfig.ConfigManager
import com.example.newsapplication.databinding.ActivityNewsBinding
import com.example.newsapplication.utils.SessionStoreManager
import com.example.newsapplication.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private var _binding: ActivityNewsBinding? = null
    val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    private val TAG = "NewsActivity"
    @Inject
    lateinit var configManager: ConfigManager

    @Inject
    lateinit var sessionStoreManager: SessionStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

       val api_key =  BuildConfig.API_KEY

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFrag) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        calculate(1, 2) { v1, v2 ->
            Log.d(TAG, "value :"+v1+"value2 "+v2)
        }


       GlobalScope.launch(Dispatchers.Main) {
            sessionStoreManager.getUserInfo().collect{ userinfo ->
                userinfo?.let {
                    showToast(it)
                }?:sessionStoreManager.setUserInfo("Testing")
            }
        }

        val list = listOf(1,2,3,4,5,6)
        for ((i,value) in list.withIndex())
        {
            println("$i - $value")
        }
    }

    fun NavController.mainNavigate(id: Int, title: String?) {
        navigate(
            id,
            bundleOf("title" to title),
            navOptions {
                launchSingleTop = true
                popUpTo(R.id.action_breakingNewsFragment_to_articleFragment) {
                    inclusive = false
                }
            }
        )

        viewModel.mapbreakingNews.observe(this@NewsActivity, Observer {
        })
    }

    fun View.updateMargin(
        top: Int = marginTop,
        start: Int = marginLeft,
        bottom: Int = marginBottom,
        end: Int = marginEnd,
    ) {
        val tempLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
        tempLayoutParams.bottomMargin = bottom
        tempLayoutParams.topMargin = top
        tempLayoutParams.marginStart = start
        tempLayoutParams.marginEnd = end
        layoutParams = tempLayoutParams
    }

    private fun calculate(a: Int, b: Int, cal: (v1: Int, v2: Int) -> Unit) {
        cal.invoke(a, b)
    }
}