package com.example.gamesapicompose.views

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
import com.example.gamesapicompose.composables.MetacriticWebsite
import com.example.gamesapicompose.composables.MetascoreCard
import com.example.gamesapicompose.composables.ShowImage
import com.example.gamesapicompose.viewmodel.GamesViewModel

@Composable
fun ContentDetailView(viewModel: GamesViewModel, paddingValues: PaddingValues) {
    val state = viewModel.state
    val scroll = rememberScrollState(0)

    Column(
        Modifier
            .padding(paddingValues)
            .background(Color.White)
    ) {
        ShowImage(image = viewModel.state.background_image)
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
            MetacriticWebsite(state.website)
            MetascoreCard(state.metacritic)
        }
        Text(
            text = state.description_raw,
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
}