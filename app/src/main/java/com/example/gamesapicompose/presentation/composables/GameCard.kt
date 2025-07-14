package com.example.gamesapicompose.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.gamesapicompose.R
import com.example.gamesapicompose.domain.model.GameList

@Composable
fun GameCard(game: GameList, onclick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_5dp)),
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.size_10dp))
            .shadow(dimensionResource(R.dimen.size_40dp))
            .clickable { onclick() }
    ) {
        Column {
            ShowImage(
                image = if (game.background_image!!.isNotEmpty()) game.background_image else stringResource(
                    R.string.no_cover_image
                )
            )
        }
    }
}