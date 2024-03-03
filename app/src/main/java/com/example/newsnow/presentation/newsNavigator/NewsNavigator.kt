package com.example.newsnow.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.home.HomeScreen
import com.example.newsnow.home.HomeViewModel
import com.example.newsnow.navGraph.Route
import com.example.newsnow.presentation.Search.SearchScreen
import com.example.newsnow.presentation.Search.SearchViewModel
import com.example.newsnow.presentation.bookmark.BookMarkScreen
import com.example.newsnow.presentation.bookmark.BookMarkViewModel
import com.example.newsnow.presentation.details.DetailsEvent
import com.example.newsnow.presentation.details.DetailsScreen
import com.example.newsnow.presentation.details.DetailsViewModel
import com.example.newsnow.presentation.newsNavigator.component.BottomNavigationItem
import com.example.newsnow.presentation.newsNavigator.component.NewsBottomNavigation



@Composable
fun NewsNavigator(){

    val bottomNavigationItem =  remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "HOME"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "SEARCH"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BOOKMARK")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember (key1 = backStackState){
        when(backStackState?.destination?.route){
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookMarkScreen.route -> 2
            else -> 0
        }
    }


    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookMarkScreen.route
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> NavigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> NavigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> NavigateToTab(
                                navController = navController,
                                route = Route.BookMarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {

        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
            ){

            composable(route = Route.HomeScreen.route){
                val viewModel : HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        NavigateToTab(navController = navController, route = Route.HomeScreen.route)
                    },
                    navigateToDetails = { article ->
                        NavigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                    )
            }

            composable(route = Route.SearchScreen.route){
                val viewModel : SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event =viewModel::onEvent,
                    navigateToDetails = { article ->
                        NavigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }

                )
            }

            composable(route = Route.DetailsScreen.route){
                val viewModel : DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                    DetailsScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            }

            composable(route = Route.BookMarkScreen.route){
                val viewModel : BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        NavigateToDetails(
                            navController = navController,
                            article = article
                            )
                    }
                )
            }



        }

    }
}

private fun NavigateToTab(navController: NavController,route : String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun NavigateToDetails(navController: NavController,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}















