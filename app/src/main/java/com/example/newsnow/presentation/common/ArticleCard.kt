package com.example.newsnow.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.model.Source
import com.example.newsnow.presentation.dimension.ExtraSmallPadding
import com.example.newsnow.presentation.dimension.ExtraSmallPadding2
import com.example.newsnow.presentation.dimension.SmallIconSize
import com.example.newsnow.ui.theme.NewsNowTheme


@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    Card(onClick = { /*TODO*/ }) {


        Row(modifier = Modifier
            .clickable { onClick() }
            .padding(start = 6.dp, end = 4.dp)
            .height(300.dp)) {


            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(start = ExtraSmallPadding)
                    .padding(horizontal = ExtraSmallPadding)
                    .height(300.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null
                )

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSystemInDarkTheme()) colorResource(id = R.color.input_background) else colorResource(
                        id = R.color.text_title
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.source.name,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(
                            id = R.color.body
                        )
                    )

                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier.size(SmallIconSize),
                        tint = if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(
                            id = R.color.black
                        )
                    )
                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                    Text(
                        text = article.publishedAt,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(
                            id = R.color.body
                        )
                    )
                }

            }

        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsNowTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC-News"),
                title = "he died because she did not want to meet him on friday night",
                url = "",
                urlToImage = ""
            )
        ) {

        }
    }

}

















