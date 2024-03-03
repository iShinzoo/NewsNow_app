package com.example.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.newsnow.navGraph.NavGraph
import com.example.newsnow.ui.theme.NewsNowTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import com.example.newsnow.data.local.NewsDAO
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.model.Source
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var dao : NewsDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        lifecycleScope.launch {
            dao.upsert(
                Article(
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
                )
            )
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

        setContent {
            NewsNowTheme {

                val  isSystemInDarkMode = isSystemInDarkTheme()
                val  systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box (modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){

                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
