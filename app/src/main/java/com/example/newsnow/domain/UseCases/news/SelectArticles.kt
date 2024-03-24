package com.example.newsnow.domain.UseCases.news

import com.example.newsnow.data.local.NewsDAO
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }

}