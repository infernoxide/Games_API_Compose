package com.example.gamesapicompose.views

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.gamesapicompose.viewmodel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel){
    val games by viewModel.games.collectAsState()

    LazyColumn{
        items(games){ item ->
            Log.d("welcome","${item.name}")
            Text(text = if(!item.name.isNullOrEmpty())item.name else "no name")
        }
    }

}