package com.example.gamesapicompose.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gamesapicompose.R
import com.example.gamesapicompose.data.datasource.GamesDataSource
import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.data.mapper.toEntity
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.domain.usescase.GetGameByIdUseCase
import com.example.gamesapicompose.domain.usescase.GetGameByNameUseCase
import com.example.gamesapicompose.domain.usescase.GetGamesByPagingUseCase
import com.example.gamesapicompose.presentation.uistate.UiState
import com.example.gamesapicompose.presentation.utils.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val stringProvider: StringProvider,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val getGamesByPagingUseCase: GetGamesByPagingUseCase,
    private val getGameByNameUseCase: GetGameByNameUseCase
) : ViewModel() {
    private var pendingAction: (() -> Unit) ?= null
    val gamesPage = Pager(PagingConfig(pageSize = 3)){
        GamesDataSource(getGamesByPagingUseCase)
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
            val result = getGameByIdUseCase(id)
            if (networkMonitor.isConnected.value) {
                gameByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    UiState.Error(stringProvider.getString(R.string.id_not_found, id.toString()))
                }
            } else {
                gameByIdState = if (result != null) {
                    UiState.Success(result)
                } else {
                    pendingAction = { getGameByID(id) }
                    UiState.Error(stringProvider.getString(R.string.no_internet_connection))
                }
            }
        }
    }

    fun getGameByName(name: String) {
        viewModelScope.launch {
            gameByIdState = UiState.Loading
            if (networkMonitor.isConnected.value) {
                val result = getGameByNameUseCase(name)
                gameByIdState = if (result != null) {
                    UiState.Success(result.toEntity())
                } else {
                    UiState.Error(stringProvider.getString(R.string.game_not_found, name))
                }
            } else {
                pendingAction = { getGameByName(name) }
                gameByIdState = UiState.Error(stringProvider.getString(R.string.no_internet_connection))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.stop()
    }

}