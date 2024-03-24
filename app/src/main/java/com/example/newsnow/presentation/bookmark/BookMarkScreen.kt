package com.example.newsnow.presentation.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.navGraph.Route
import com.example.newsnow.presentation.common.ArticlesList
import com.example.newsnow.presentation.dimension.MediumPadding1


@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .background(Color.White)
    ) {

        Text(
            text = "BookMark",
            style = MaterialTheme.typography.displayMedium
                .copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.black)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(articles = state.articles, onClick = { navigateToDetails(it) })

    }
}