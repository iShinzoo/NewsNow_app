package com.example.newsnow.presentation.common


import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.newsnow.R
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun EmptyScreen(error : LoadState.Error? = null) {

    var Message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    var Icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }
    if (error == null) {
        Message = "You have not Saved News So far !"
        Icon = R.drawable.ic_search_document
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    EmptyContent(
        alphaAnim = alphaAnimation,
        message = Message,
        iconId = Icon
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
        
        Icon(painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
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
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview(){
    EmptyContent(alphaAnim = 0.3f , message = "Internet Unavailable",R.drawable.ic_network_error)
}