package com.arslan.newsapp_wipro.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arslan.newsapp_wipro.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(com.arslan.newsapp_wipro.db.Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): com.arslan.newsapp_wipro.db.ArticleDao
}