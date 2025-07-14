package com.example.gamesapicompose.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gamesapicompose.R
import com.example.gamesapicompose.presentation.composables.MainTopBar
import com.example.gamesapicompose.presentation.uistate.UiState
import com.example.gamesapicompose.presentation.viewmodel.GamesViewModel

@Composable
fun DetailView(viewModel: GamesViewModel, navController: NavController, id:Int, name:String?){
    LaunchedEffect(Unit) {
        if (id == 0){
            name?.let { value ->
                viewModel.getGameByName(value.replace(" ","-"))
            }
        }else{
            viewModel.getGameByID(id)
        }
    }
    Scaffold(
        topBar = {
            MainTopBar(title = setTitle(viewModel), showBackButton = true) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        ContentDetailView(viewModel, paddingValues)
    }
}

@Composable
fun setTitle(viewModel: GamesViewModel):String{
    return when (val state = viewModel.gameByIdState){
        is UiState.Loading -> stringResource(R.string.loading)
        is UiState.Success -> state.data.name
        is UiState.Error -> stringResource(R.string.error)
    }
}