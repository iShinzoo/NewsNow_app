package com.example.newsnow.presentation.bookmark

import com.example.newsnow.domain.model.Article

data class BookMarkState(
    val articles: List<Article> = emptyList()
)
