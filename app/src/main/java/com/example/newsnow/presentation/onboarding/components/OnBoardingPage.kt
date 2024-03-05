package com.example.newsnow.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsnow.R
import com.example.newsnow.presentation.dimension.MediumPadding1
import com.example.newsnow.presentation.dimension.MediumPadding2
import com.example.newsnow.presentation.onboarding.Page
import com.example.newsnow.presentation.onboarding.page
import com.example.newsnow.ui.theme.NewsNowTheme


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){

    Column(modifier = Modifier) {

        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            ,painter = painterResource(id = page.image)
            , contentDescription = null,
            contentScale = ContentScale.Crop,
            )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = page.title,
            modifier = Modifier
                .padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = if(isSystemInDarkTheme())colorResource(id = R.color.input_background) else Color.Black
            )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = page.description,
            modifier = Modifier
                .padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = if(isSystemInDarkTheme())colorResource(id = R.color.input_background)
            else colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES,showBackground = true)
@Composable
fun onBoardingPagePreview(){
    NewsNowTheme {
        OnBoardingPage(
            page = page[0]
        )
    }
}