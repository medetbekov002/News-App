package com.example.newsapp.repository.datasource

import androidx.paging.DataSource
import com.example.newsapp.model.Article
import kotlinx.coroutines.CoroutineScope

class ArticleDataSourceFactory(private val source: CoroutineScope): DataSource.Factory<Int, Article>() {
    val articleSourceLiveData
}