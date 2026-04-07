package com.example.gamesapicompose.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gamesapicompose.R
import com.example.gamesapicompose.presentation.uistate.UiState
import com.example.gamesapicompose.presentation.viewmodel.GamesViewModel

@Composable
fun setTitle(viewModel: GamesViewModel):String{
    return when (val state = viewModel.gameByIdState){
        is UiState.Loading -> stringResource(R.string.loading)
        is UiState.Success -> state.data.name
        is UiState.Error -> stringResource(R.string.error)
    }
}