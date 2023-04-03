package com.example.newsapp.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.bumptech.glide.load.engine.Resource
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.service.RetrofitClient
import com.example.newsapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticleDataSource(val source: CoroutineScope) : PageKeyedDataSource<Int, Article>() {

    // for breaking news
    val breakingNews: MutableLiveData<MutableList<Article>> = MutableLiveData()
    val breakingPageNumber = 1
    val breakingNewsResponse: NewsResponse? = null

    // for search news
    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchPageNumber = 1
    val searchNewsResponse: NewsResponse? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        source.launch {
            try {
                val response = RetrofitClient.api.getBreakingNews("in", 1, Constants.API_KEY)
                when {
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            breakingNews.postValue(it)
                            callback.onResult(it, null, 2)
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.e("DataSource::", exception.message.toString())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        try {
            source.launch {
                val response = RetrofitClient.api.getBreakingNews(
                    "in",
                    params.requestedLoadSize,
                    Constants.API_KEY
                )
                when {
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            callback.onResult(it, params.key + 1)
                        }
                    }
                }
            }
        } catch (exception: Exception) {
            Log.e("DataSource::", exception.message.toString())
        }
    }
}