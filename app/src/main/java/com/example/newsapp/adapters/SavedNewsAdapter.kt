package com.example.newsapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.Article


class SavedNewsAdapter : RecyclerView.Adapter<SavedNewsAdapter.SavedViewHolder>() {


    private val diffUtilCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == oldItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

    }


    inner class SavedViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    val differ = AsyncListDiffer(this, diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArticleBinding>(
            inflater,
            R.layout.item_saved_news,
            parent,
            false
        )
        return SavedViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.view.article = article

        // item click listener
        // bind these click listener later
        holder.itemView.setOnClickListener {
            onItemClickListener.let {
                article.let { article ->
                    it(article)
                }
            }
        }

        holder.view.ivShare.setOnClickListener {
            onShareNewsClick.let {
                article.let { article ->
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article)) -> Util)? = null
    internal var onShareNewsClick: ((Article)) -> Util)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    fun onShareClickListener(listener: ((Article) -> Util)) {
        onShareNewsClick = listener
    }

}