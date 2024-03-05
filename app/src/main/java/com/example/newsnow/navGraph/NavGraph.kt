package com.example.newsnow.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newsnow.presentation.bookmark.BookMarkScreen
import com.example.newsnow.presentation.bookmark.BookMarkViewModel
import com.example.newsnow.presentation.newsNavigator.NewsNavigator
import com.example.newsnow.presentation.onboarding.OnBoardingScreen
import com.example.newsnow.presentation.onboarding.OnBoardingViewModel


@Composable
fun NavGraph(
    startDestination : String
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
                ){
                val viewModel : OnBoardingViewModel = hiltViewModel()

                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                NewsNavigator()
            }
        }
    }

}