package com.example.gamesapicompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamesapicompose.core.constants.Constants.Companion.NAV_DETAIL_VIEW
import com.example.gamesapicompose.core.constants.Constants.Companion.NAV_HOME
import com.example.gamesapicompose.presentation.viewmodel.GamesViewModel
import com.example.gamesapicompose.presentation.views.DetailView
import com.example.gamesapicompose.presentation.views.HomeView

@Composable
fun NavManager(viewModel: GamesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NAV_HOME) {
        composable(route = NAV_HOME) {
            HomeView(viewModel = viewModel, navController)
        }
        composable(
            route = "$NAV_DETAIL_VIEW/{id}/?{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType}
            )) { navStackEntry ->
            val id = navStackEntry.arguments?.getInt("id") ?: 0
            val name = navStackEntry.arguments?.getString("name") ?: ""
            DetailView(viewModel = viewModel, navController, id, name)
        }
    }
}