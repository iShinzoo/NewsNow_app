package com.example.newsnow.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsnow.data.local.NewsDAO
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository
import com.example.newsnow.remote.NewsApi
import com.example.newsnow.remote.NewsPagingSource
import com.example.newsnow.remote.SearchNewsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDAO: NewsDAO
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDAO.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDAO.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDAO.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDAO.getArticle(url)
    }

}