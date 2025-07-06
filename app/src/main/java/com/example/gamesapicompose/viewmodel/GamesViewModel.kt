package com.example.gamesapicompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gamesapicompose.data.GamesDataSource
import com.example.gamesapicompose.model.GameList
import com.example.gamesapicompose.repository.GamesRepository
import com.example.gamesapicompose.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val repository: GamesRepository) : ViewModel() {
    private val _games = MutableStateFlow<List<GameList>>(emptyList())
    var state by mutableStateOf(GameState())
        private set

    init {
        fetchGames()
    }

    val gamesPage = Pager(PagingConfig(pageSize = 3)){
        GamesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    private fun fetchGames(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repository.getGames()
                _games.value = result ?: emptyList()
            }
        }
    }

    fun getGameByID(id:Int){
        viewModelScope.launch {
            val result = repository.getGameByID(id)
            state = state.copy(
                name = result?.name ?: "",
                description_raw = result?.description_raw ?: "",
                metacritic = result?.metacritic ?: 666,
                website = result?.website ?: "",
                background_image = result?.background_image ?: ""
            )
        }
    }

    fun clearState(){
        state = state.copy(
            name = "",
            description_raw = "",
            metacritic = 0,
            website = "",
            background_image = ""
        )
    }

}