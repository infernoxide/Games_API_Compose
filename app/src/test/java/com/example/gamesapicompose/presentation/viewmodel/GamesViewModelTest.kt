package com.example.gamesapicompose.presentation.viewmodel

import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.domain.usescase.GetGameByIdUseCase
import com.example.gamesapicompose.domain.usescase.GetGameByNameUseCase
import com.example.gamesapicompose.domain.usescase.GetGamesByPagingUseCase
import com.example.gamesapicompose.presentation.uistate.UiState
import com.example.gamesapicompose.presentation.utils.StringProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class GamesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: GamesViewModel

    private val networkMonitor: NetworkMonitor = mock()
    private val stringProvider: StringProvider = mock()
    private val getGameByIdUseCase: GetGameByIdUseCase = mock()
    private val getGamesByPagingUseCase: GetGamesByPagingUseCase = mock()
    private val getGameByNameUseCase: GetGameByNameUseCase = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        whenever(networkMonitor.isConnected).thenReturn(MutableStateFlow(true))
        viewModel = GamesViewModel(
            networkMonitor,
            stringProvider,
            getGameByIdUseCase,
            getGamesByPagingUseCase,
            getGameByNameUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getGameByID returns success when game is found`() = runTest {
        val game = DetailGame(1, "Skyrim", "Una aventura medieval", 95, "www.google.com", "imageURK")
        whenever(getGameByIdUseCase(1)).thenReturn(game)

        viewModel.getGameByID(1)
        advanceUntilIdle()

        assert(viewModel.gameByIdState is UiState.Success)
        assertEquals(game, (viewModel.gameByIdState as UiState.Success).data)
    }

}
