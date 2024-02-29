package com.example.newsnow.remote.dto

import com.example.newsnow.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)