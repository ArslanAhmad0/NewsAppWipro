package com.arslan.newsapp_wipro.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arslan.newsapp_wipro.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}