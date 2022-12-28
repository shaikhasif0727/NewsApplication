package com.example.newsapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapplication.ui.NewsViewModel
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentArticleBinding
import com.example.newsapplication.models.Article
import com.example.newsapplication.ui.NewsActivity
import com.example.newsapplication.ui.common.BaseVBFragment
import com.example.newsapplication.utils.SessionStoreManager
import com.example.newsapplication.utils.showSnakBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ArticleFragment : BaseVBFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate) {

    val viewModel: NewsViewModel by activityViewModels()
    val args: ArticleFragmentArgs by navArgs()

    @Inject
    lateinit var sessionStoreManager : SessionStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            showSnakBar("Article saved successfully")
            storeUserInfo(article)
        }
    }

    private fun storeUserInfo(article: Article){
        GlobalScope.launch {
            article.url?.let { sessionStoreManager.setUserInfo(it) }
        }
    }
}