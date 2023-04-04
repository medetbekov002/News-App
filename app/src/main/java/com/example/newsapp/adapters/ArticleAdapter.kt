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


class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == oldItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return newItem.title == newItem.title
            }

        }
    }

    class ArticleViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    val differ = AsyncListDiffer(this, diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArticleBinding>(
            inflater,
            R.layout.item_article,
            parent,
            false
        )
        return ArticleViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
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

        holder.view.ivSave.setOnClickListener {
            if (holder.view.ivSave.tag.toString().toInt() == 0) {
                holder.view.ivSave.tag = 1
                holder.view.ivSave.setImageDrawable(it.resources.getDrawable(R.drawable.ic_saved))
                onArticleSaveClick?.let {
                    if (article != null) {
                        it(article)
                    }
                }
            } else {
                holder.view.ivSave.tag = 0
                holder.view.ivSave.setImageDrawable(it.resources.getDrawable(R.drawable.ic_save))
                onArticleSaveClick?.let {
                    if (article != null) {
                        it(article)
                    }
                }
            }
            onArticleSaveClick?.let {
                article?.let{itl -> it(itl)}
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

    var isSave = false

    override fun getItemId(position: Int) = position.toLong()

    private var onItemClickListener: ((Article)) -> Util)? = null
    private var onArticleSaveClick: ((Article)) -> Util)? = null
    private var onArticleDeleteClick: ((Article)) -> Util)? = null
    internal var onShareNewsClick: ((Article)) -> Util)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    fun onSaveClickListener(listener: (Article) -> Unit) {
        onArticleSaveClick = listener
    }

    fun onDeleteClickListener(listener: (Article) -> Unit) {
        onArticleDeleteClick = listener
    }

    fun onShareClickListener(listener: ((Article) -> Util)) {
        onShareNewsClick = listener
    }

}