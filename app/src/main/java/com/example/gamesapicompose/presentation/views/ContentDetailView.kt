package com.example.gamesapicompose.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.gamesapicompose.R
import com.example.gamesapicompose.presentation.composables.MetacriticWebsite
import com.example.gamesapicompose.presentation.composables.MetascoreCard
import com.example.gamesapicompose.presentation.composables.ShimmerDetailView
import com.example.gamesapicompose.presentation.composables.ShowImage
import com.example.gamesapicompose.presentation.uistate.UiState
import com.example.gamesapicompose.presentation.viewmodel.GamesViewModel

@Composable
fun ContentDetailView(viewModel: GamesViewModel, paddingValues: PaddingValues) {
    val scroll = rememberScrollState(0)
    val stateLoading = viewModel.gameByIdState

    Column(
        Modifier
            .padding(paddingValues)
            .background(Color.White)
    ) {

        when (stateLoading) {
            is UiState.Loading -> ShimmerDetailView()
            is UiState.Success -> {
                ShowImage(image = stateLoading.data.background_image)
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_10dp)))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(R.dimen.size_20dp),
                            end = dimensionResource(R.dimen.size_5dp)
                        )
                ) {
                    MetacriticWebsite(stateLoading.data.website)
                    MetascoreCard(stateLoading.data.metacritic)
                }
                Text(
                    text = stateLoading.data.description_raw,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.size_15dp),
                            vertical = dimensionResource(R.dimen.size_10dp)
                        )
                        .verticalScroll(scroll)
                )
            }
            is UiState.Error -> {
                ErrorView(
                    error = stateLoading.message,
                    onRetry = {}
                )
            }
        }
    }
}