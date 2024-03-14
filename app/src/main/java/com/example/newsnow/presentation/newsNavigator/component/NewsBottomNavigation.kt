package com.example.newsnow.presentation.newsNavigator.component


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults.IconSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsnow.R
import com.example.newsnow.presentation.dimension.ExtraSmallPadding2
import com.example.newsnow.ui.theme.NewsNowTheme


@Composable
fun NewsBottomNavigation(
    items : List<BottomNavigationItem>,
    selected : Int,
    onItemClick : (Int) -> Unit
){

    NavigationBar (
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ){
        items.forEachIndexed{ index , item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                        
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )

        }
    }

}


data class BottomNavigationItem(
    @DrawableRes val icon : Int,
    val text : String
)


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview(){
    NewsNowTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(icon = R.drawable.home, text = "HOME"),
                BottomNavigationItem(icon = R.drawable.search, text = "SEARCH"),
                BottomNavigationItem(icon = R.drawable.bookmark, text = "BOOKMARK")
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}