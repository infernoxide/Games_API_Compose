package com.example.gamesapicompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamesapicompose.util.Constants.Companion.NAV_DETAIL_VIEW
import com.example.gamesapicompose.util.Constants.Companion.NAV_HOME
import com.example.gamesapicompose.viewmodel.GamesViewModel
import com.example.gamesapicompose.views.DetailView
import com.example.gamesapicompose.views.HomeView

@Composable
fun NavManager(viewModel: GamesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NAV_HOME) {
        composable(route = NAV_HOME) {
            HomeView(viewModel = viewModel, navController)
        }
        composable(route = "$NAV_DETAIL_VIEW/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { navStackEntry ->
            val id = navStackEntry.arguments?.getInt("id") ?: 0
            DetailView(viewModel = viewModel, navController, id)
        }
    }
}