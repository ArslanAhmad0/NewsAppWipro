package com.arslan.newsapp_wipro.repository

import com.arslan.newsapp_wipro.db.ArticleDao
import com.arslan.newsapp_wipro.model.Article

import javax.inject.Inject

class LocaleRepository @Inject constructor(
    val db: com.arslan.newsapp_wipro.db.ArticleDao
) : BaseRepository() {

    suspend fun insertNewsToDb(article: Article) = db.insertArticle(article)

    suspend fun deleteNews(article: Article) = db.deleteArticle(article)

    fun getSavedNews() = db.getSavedArticles()
}