package com.example.newsapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("author")
    var author: String,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("tittle")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
) : Serializable
