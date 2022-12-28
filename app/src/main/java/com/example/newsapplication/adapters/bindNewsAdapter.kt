package com.example.newsapplication.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.databinding.ItemArticlePreviewBinding
import com.example.newsapplication.models.Article
import com.example.newsapplication.utils.loadWithShimmer

fun bindNewsAdapter(
    newsClickCallback: (Article) -> Unit,
) : SimpleListAdapter<ItemArticlePreviewBinding,Article> {
    val adapter = SimpleListAdapter(ItemArticlePreviewBinding::inflate,
        onBind = { _: Int, itemView: ItemArticlePreviewBinding, data: Article ->

            itemView.apply {
                Glide.with(itemView.root.context)
                    .loadWithShimmer(data.urlToImage)
                    .into(itemView.ivArticleImage)
                itemView.tvSource.text = data.source?.name
                itemView.tvTitle.text = data.title
                itemView.tvDescription.text = data.description
                itemView.tvPublishedAt.text = data.publishedAt
            }

        },
        itemComparator = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        } ,
        onItemSelectListener = {
            newsClickCallback(it)
        },
    )
   return adapter
}