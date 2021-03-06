package com.arslan.newsapp_wipro.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arslan.newsapp_wipro.MainCoroutineRule
import com.arslan.newsapp_wipro.model.Article
import com.arslan.newsapp_wipro.model.Source
import com.arslan.newsapp_wipro.repository.LocaleRepository
import com.arslan.newsapp_wipro.repository.RemoteRepository
import com.arslan.newsapp_wipro.ui.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    /**
     * just make sure that everything will run in the same thread
     * */
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @InjectMocks
    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var remoteRepository: RemoteRepository

    @Mock
    private lateinit var localeRepository: LocaleRepository

    @Before
    fun setUp() {
        viewModel = NewsViewModel(remoteRepository, localeRepository)
        Mockito.reset(remoteRepository, localeRepository)
    }

    @Test
    fun testGetBreakingNews() = coroutinesTestRule.dispatcher.runBlockingTest {
        // Given

        // When
        viewModel.getBreakingNews()

        // Then
        verify(remoteRepository).getBreakingNews()
    }

    @Test
    fun testSearchNews() = coroutinesTestRule.dispatcher.runBlockingTest {
        // Given
        val dummy = "bitcoin"

        // When
        viewModel.searchNews(dummy)

        // Then
        verify(remoteRepository).searchNews(dummy)
    }

    @Test
    fun testSaveNews() = coroutinesTestRule.dispatcher.runBlockingTest {
        // Given
        val article = Article(
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

        // When
        viewModel.saveNews(article)

        // Then
        verify(localeRepository).insertNewsToDb(article)
    }

    @Test
    fun testDeleteNews() = coroutinesTestRule.dispatcher.runBlockingTest {
        // Given
        val article = Article(
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

        // When
        viewModel.deleteNews(article)

        // Then
        verify(localeRepository).deleteNews(article)
    }

    @Test
    fun testGetSavedNews() {
        // Given

        // When
        viewModel.getSavedNews()

        // Then
        verify(localeRepository).getSavedNews()
    }
}

