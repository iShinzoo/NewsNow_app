package com.example.newsnow.domain.repository

import androidx.paging.PagingData
import com.example.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources : List<String>) : Flow<PagingData<Article>>

}