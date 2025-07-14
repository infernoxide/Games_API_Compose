package com.example.gamesapicompose.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gamesapicompose.R
import com.example.gamesapicompose.data.GamesDataSource
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.local.DetailGame
import com.example.gamesapicompose.model.mapper.toEntity
import com.example.gamesapicompose.repository.GamesRepository
import com.example.gamesapicompose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: GamesRepository,
    private val networkMonitor: NetworkMonitor,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private var pendingAction: (() -> Unit) ?= null
    val gamesPage = Pager(PagingConfig(pageSize = 3)){
        GamesDataSource(repository)
    }.flow.cachedIn(viewModelScope)
    var gameByIdState: UiState<DetailGame> by mutableStateOf(UiState.Loading)
        private set

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
            gameByIdState = UiState.Loading
            val result = repository.getGameByID(id)
            if (networkMonitor.isConnected.value) {
                gameByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    UiState.Error(context.getString(R.string.id_not_found, id.toString()))
                }
            } else {
                gameByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    pendingAction = { getGameByID(id) }
                    UiState.Error(context.getString(R.string.no_internet_connection))
                }
            }
        }
    }

    fun getGameByName(name: String) {
        viewModelScope.launch {
            gameByIdState = UiState.Loading
            if (networkMonitor.isConnected.value) {
                val result = repository.getGameByName(name)
                gameByIdState = if (result != null) {
                    UiState.Success(result.toEntity())
                } else {
                    UiState.Error(context.getString(R.string.game_not_found, name))
                }
            } else {
                pendingAction = { getGameByName(name) }
                gameByIdState = UiState.Error(context.getString(R.string.no_internet_connection))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.stop()
    }

}