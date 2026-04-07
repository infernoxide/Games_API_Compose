package com.example.gamesapicompose.presentation.views

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.gamesapicompose.presentation.composables.MainTopBar
import com.example.gamesapicompose.presentation.composables.setTitle
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

