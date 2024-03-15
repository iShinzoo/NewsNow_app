package com.example.newsnow.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.model.Source
import com.example.newsnow.presentation.details.components.DetailsTopBar
import com.example.newsnow.presentation.dimension.ArticleImageHeight
import com.example.newsnow.presentation.dimension.MediumPadding1
import com.example.newsnow.ui.theme.NewsNowTheme


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {

            item {

                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.text_medium)
                )

            }

        }

    }
}


@Preview(showBackground = true)

@Composable
fun DetailsScreenPreview() {
    NewsNowTheme(dynamicColor = false) {


        DetailsScreen(
            article = Article(
                author = "",
                title = "What’s Article 370? What to know about India top court verdict on Kashmir",
                description = "What’s Article 370? What to know about India top court verdict on Kashmir",
                content = "In a major setback to Kashmiri political groups," +
                        " India’s Supreme Court has upheld a 2019 decision by Prime Minister Narendra Modi’s" +
                        " government to revoke special status for Indian-administered Kashmir," +
                        " which gave it a degree of autonomy.\n" +
                        "\n" +
                        "The disputed Himalayan region is claimed in full although" +
                        " ruled in part by both India and Pakistan since their independence" +
                        " from Britain in 1947. The nuclear-armed neighbours have fought three" +
                        " of their four wars over it since then.",
                publishedAt = "",
                source = Source(
                    id = "", name = ""
                ),
                url = "",
                urlToImage = ""
            ),
            event = {}) {
        }
    }

}


