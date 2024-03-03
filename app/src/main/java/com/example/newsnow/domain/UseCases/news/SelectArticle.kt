package com.example.newsnow.domain.UseCases.news

import com.example.newsnow.data.local.NewsDAO
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String) : Article? {
        return newsRepository.selectArticle(url)
    }
}