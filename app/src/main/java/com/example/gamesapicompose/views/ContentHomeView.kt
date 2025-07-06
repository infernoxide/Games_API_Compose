package com.example.gamesapicompose.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gamesapicompose.R
import com.example.gamesapicompose.composables.GameCard
import com.example.gamesapicompose.util.Constants.Companion.NAV_DETAIL_VIEW
import com.example.gamesapicompose.viewmodel.GamesViewModel

@Composable
fun ContentHomeView(viewModel: GamesViewModel, paddingValues: PaddingValues, navController: NavController) {
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = dimensionResource(R.dimen.size_10dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text(text = stringResource(R.string.search)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    val zero = 0
                    navController.navigate("$NAV_DETAIL_VIEW/${zero}/?${search}")
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.size_3dp))
        )

        LazyColumn {
            items(gamesPage.itemCount) { index ->
                val item = gamesPage[index]
                if (item != null) {
                    GameCard(item) {
                        navController.navigate("$NAV_DETAIL_VIEW/${item.id}/?${search}")
                    }
                    Text(
                        text = if (item.name!!.isNotEmpty()) item.name else stringResource(R.string.empty_name),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                }
            }
        }
    }

}