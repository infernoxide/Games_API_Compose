package com.example.gamesapicompose.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gamesapicompose.R
import com.example.gamesapicompose.presentation.composables.MainTopBar
import com.example.gamesapicompose.presentation.viewmodel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel, navController: NavController) {

    Scaffold(
        topBar = {
            MainTopBar(title = stringResource(R.string.title_home_view)) { }
        }

    ) { paddingValues ->
        ContentHomeView(viewModel, paddingValues, navController)
    }

}