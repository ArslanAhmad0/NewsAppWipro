package com.arslan.newsapp_wipro.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.arslan.newsapp_wipro.getOrAwaitValue
import com.arslan.newsapp_wipro.model.Article
import com.arslan.newsapp_wipro.model.Source
import com.google.common.truth.Truth.assertThat
import com.arslan.newsapp_wipro.db.ArticleDao
import com.arslan.newsapp_wipro.db.ArticleDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArticleDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: com.arslan.newsapp_wipro.db.ArticleDatabase
    private lateinit var dao: com.arslan.newsapp_wipro.db.ArticleDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.getArticleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArticleItem() = runBlockingTest {
        val articleItem = Article(
            1,
            "author",
            "content",
            "description",
            "publishedAt",
            Source(1, "name"),
            "title",
            "url",
            "urlToImage"
        )
        dao.insertArticle(articleItem)

        val allArticleItems = dao.getSavedArticles().getOrAwaitValue()

        // assertThat(allArticleItems).contains(articleItem)
    }

    @Test
    fun deleteArticleItem() = runBlockingTest {
        val articleItem = Article(
            1,
            "author",
            "content",
            "description",
            "publishedAt",
            Source(1, "name"),
            "title",
            "url",
            "urlToImage"
        )
        dao.insertArticle(articleItem)
        dao.deleteArticle(articleItem)

        val allArticleItems = dao.getSavedArticles().getOrAwaitValue()

        assertThat(allArticleItems).doesNotContain(articleItem)
    }
}