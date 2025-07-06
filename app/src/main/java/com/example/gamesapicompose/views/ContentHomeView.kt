package com.example.gamesapicompose.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gamesapicompose.R
import com.example.gamesapicompose.composables.GameCard
import com.example.gamesapicompose.util.Constants.Companion.NAV_DETAIL_VIEW
import com.example.gamesapicompose.viewmodel.GamesViewModel

@Composable
fun ContentHomeView(viewModel: GamesViewModel, paddingValues: PaddingValues, navController: NavController) {
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(paddingValues = paddingValues)) {
        items(gamesPage.itemCount) { index ->
            val item = gamesPage[index]
            if (item != null) {
                GameCard(item) {
                    navController.navigate("$NAV_DETAIL_VIEW/${item.id}")
                }
                Text(
                    text = if (item.name!!.isNotEmpty()) item.name else stringResource(R.string.empty_name),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.size_10dp))
                )
            }
        }
    }

}