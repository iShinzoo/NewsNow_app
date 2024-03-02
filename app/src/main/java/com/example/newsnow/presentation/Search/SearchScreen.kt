package com.example.newsnow.presentation.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsnow.navGraph.Route
import com.example.newsnow.presentation.common.ArticlesList
import com.example.newsnow.presentation.common.SearchBar
import com.example.newsnow.presentation.dimension.MediumPadding1


@Composable
fun SearchScreen(
    state: SearchState,
    event : (SearchEvent) -> Unit,
    navigate : (String) -> Unit
){

    Column (
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        SearchBar(
            text =state.searchQuery,
            readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)})
        
        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigate(Route.DetailsScreen.route)})

        }

    }

}