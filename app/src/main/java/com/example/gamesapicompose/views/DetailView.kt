package com.example.gamesapicompose.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.gamesapicompose.composables.MainTopBar
import com.example.gamesapicompose.viewmodel.GamesViewModel

@Composable
fun DetailView(viewModel: GamesViewModel, navController: NavController, id:Int){
    LaunchedEffect(Unit) {
        viewModel.getGameByID(id)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearState()
        }
    }
    Scaffold(
        topBar = {
            MainTopBar(title = viewModel.state.name, showBackButton = true) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        ContentDetailView(viewModel, paddingValues)
    }
}