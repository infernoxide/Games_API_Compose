package com.example.gamesapicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.gamesapicompose.viewmodel.GamesViewModel
import com.example.gamesapicompose.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: GamesViewModel by viewModels()
        setContent {
            HomeView(viewModel)
        }
    }
}