package com.example.newsnow.domain.UseCases.news

import com.example.newsnow.data.local.NewsDAO
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }

}