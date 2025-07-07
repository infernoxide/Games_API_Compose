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
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.repository.GamesRepository
import com.example.gamesapicompose.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: GamesRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {
    private var pendingAction: (() -> Unit) ?= null
    var state by mutableStateOf(GameState())
        private set
    val gamesPage = Pager(PagingConfig(pageSize = 3)){
        GamesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    init {
        networkMonitor.start()

        viewModelScope.launch {
            networkMonitor.isConnected.collect { isConnected ->
                if (isConnected) {
                    pendingAction?.invoke()
                    pendingAction = null
                }
            }
        }
    }

    fun getGameByID(id: Int) {
        viewModelScope.launch {
            if (networkMonitor.isConnected.value) {
                val result = repository.getGameByID(id)
                state = state.copy(
                    name = result?.name ?: "",
                    description_raw = result?.description_raw ?: "",
                    metacritic = result?.metacritic ?: 101,
                    website = result?.website ?: "",
                    background_image = result?.background_image ?: ""
                )
            } else {
                pendingAction = { getGameByID(id) }
            }
        }
    }

    fun getGameByName(name: String) {
        viewModelScope.launch {
            if (networkMonitor.isConnected.value) {
                val result = repository.getGameByName(name)
                state = state.copy(
                    name = result?.name ?: "",
                    description_raw = result?.description_raw ?: "",
                    metacritic = result?.metacritic ?: 101,
                    website = result?.website ?: "",
                    background_image = result?.background_image ?: ""
                )
            } else {
                pendingAction = { getGameByName(name) }
            }
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

    override fun onCleared() {
        super.onCleared()
        networkMonitor.stop()
    }

}