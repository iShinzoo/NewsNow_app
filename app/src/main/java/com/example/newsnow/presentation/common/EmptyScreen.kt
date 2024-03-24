package com.example.newsnow.presentation.common


import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.newsnow.R
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun EmptyScreen(error : LoadState.Error? = null) {

    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    var icon by remember {
        mutableIntStateOf(R.drawable.serverdown)
    }
    if (error == null) {
        message = "You have not Saved News So far !"
        icon = R.drawable.bookmarkkk
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    EmptyContent(
        alphaAnim = alphaAnimation,
        message = message,
        iconId = icon
    )

}
@Composable
fun EmptyContent(
    alphaAnim : Float,
    message : String,
    iconId: Int
){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.2f),
            painter = painterResource(id = iconId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim)
        )

    }
}

fun parseErrorMessage(error : LoadState.Error?): String{

    return when(error?.error){
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "Internet unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview(){
    EmptyContent(alphaAnim = 0.3f , message = "Internet Unavailable",R.drawable.bookmarkkk)
}