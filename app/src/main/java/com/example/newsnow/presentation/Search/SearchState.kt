package com.example.newsnow.presentation.Search

import androidx.paging.PagingData
import com.example.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {

}