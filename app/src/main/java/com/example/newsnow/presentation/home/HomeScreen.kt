package com.example.newsnow.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.presentation.common.ArticlesList
import com.example.newsnow.presentation.common.SearchBar
import com.example.newsnow.presentation.dimension.MediumPadding1


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier.background(Color.White),
    articles: LazyPagingItems<Article>,
//    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {


//    val titles by remember {
//        derivedStateOf {
//            if (articles.itemCount > 10) {
//                articles.itemSnapshotList.items
//                    .slice(IntRange(start = 0, endInclusive = 9))
//                    .joinToString(separator = " \uD83d\uDFE5 ") {
//                        it.title
//                    }
//            } else {
//                ""
//            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {


            Image(
                painter = painterResource(id = R.drawable.n),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp)
            )
        }

//        Spacer(modifier = Modifier.height(MediumPadding1))

        // Animated Headline
//        Text(
//            text = titles,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = MediumPadding1)
//                .basicMarquee(),
//            fontSize = 12.sp,
//            color = Color.Black
//        )

        Spacer(modifier = Modifier.height(MediumPadding1))


        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            })
    }

}
