package com.example.newsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (
    val newsRepository: NewsRepository
): ViewModel() {
    // for breaking news
    val breakingNews: MutableLiveData<Resource<NewsRepository>> = MutableLiveData()
    val breakingPageNumber = 1
    val breakingNewsResponse: NewsResponse? = null

    // for search news
    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchPageNumber = 1
    val searchNewsResponse: NewsResponse? = null

    lateinit var articles: LiveData<PagedList<Article>>

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(com.example.newsapp.utils.Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingPageNumber)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsRepository>? {

    }
}