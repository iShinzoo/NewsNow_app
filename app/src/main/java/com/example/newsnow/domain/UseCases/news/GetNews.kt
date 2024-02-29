package com.example.newsnow.domain.UseCases.news

import androidx.paging.PagingData
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository) {

    operator fun invoke(sources : List<String>) : Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }

}