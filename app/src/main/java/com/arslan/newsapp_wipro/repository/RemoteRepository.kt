package com.arslan.newsapp_wipro.repository

import com.arslan.newsapp_wipro.model.Article
import com.arslan.newsapp_wipro.network.NewsApi
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val newsApi: NewsApi
) : BaseRepository() {

    suspend fun getBreakingNews(): MutableList<Article>? {
        return safeApiCall(
            call = { newsApi.getBreakingNews() },
            error = "Error fetching news"
        )?.articles?.toMutableList()
    }

    suspend fun searchNews(searchQuery: String): MutableList<Article>? {
        return safeApiCall(
            call = { newsApi.searchForNews(searchQuery) },
            error = "Error fetching news"
        )?.articles?.toMutableList()
    }
}