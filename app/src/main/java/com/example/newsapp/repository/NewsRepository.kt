package com.example.newsapp.repository

import com.example.newsapp.model.Article
import com.example.newsapp.repository.db.ArticleDataBase
import com.example.newsapp.repository.service.RetrofitClient

class NewsRepository(
    val db: ArticleDataBase
) {
    suspend fun getBreakingNews(countrycode: String, pageNumber: Int) =
        RetrofitClient.api.getBreakingNews(countrycode, pageNumber)

    suspend fun getSearchNews(countrycode: String, pageNumber: Int) =
        RetrofitClient.api.getSearchNews(countrycode, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().insert(article)
    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getAllArticles() = db.getArticleDao().getArticles()
}