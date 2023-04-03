package com.example.newsapp.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp.model.Article
import kotlinx.coroutines.CoroutineScope

class ArticleDataSourceFactory(private val source: CoroutineScope): DataSource.Factory<Int, Article>() {
    val articleDataSourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val newArticleDataSource = ArticleDataSource(source)
        articleDataSourceLiveData.postValue(newArticleDataSource)
        return newArticleDataSource
    }

}