package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.domain.model.SingleGameModel
import com.example.gamesapicompose.domain.repository.GamesRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetGameByNameUseCaseTest {

    private lateinit var useCase: GetGameByNameUseCase
    private val repository: GamesRepository = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetGameByNameUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return game when repository returns game`() = runTest {
        val name = "Borderlands 4"
        val expectedGame = mock<SingleGameModel>()
        whenever(repository.getGameByName(name)).thenReturn(expectedGame)

        val result = useCase(name)

        assertEquals(expectedGame, result)
        verify(repository).getGameByName(name)
    }

    @Test
    fun `invoke should return null when repository returns null`() = runTest {
        val name = "Juego desconocido"
        whenever(repository.getGameByName(name)).thenReturn(null)

        val result = useCase(name)

        assertNull(result)
        verify(repository).getGameByName(name)
    }
}