package com.arslan.newsapp_wipro.model


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)