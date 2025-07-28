package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.data.local.room.entities.DetailGame
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
class GetGameByIdUseCaseTest {

    private lateinit var useCase: GetGameByIdUseCase
    private val repository: GamesRepository = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetGameByIdUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return game when repository returns game`() = runTest {
        val id = 1
        val expectedGame = mock<DetailGame>()
        whenever(repository.getGameByID(id)).thenReturn(expectedGame)

        val result = useCase(id)

        assertEquals(expectedGame, result)
        verify(repository).getGameByID(id)
    }

    @Test
    fun `invoke should return null when repository returns null`() = runTest {
        val id = 999
        whenever(repository.getGameByID(id)).thenReturn(null)

        val result = useCase(id)

        assertNull(result)
        verify(repository).getGameByID(id)
    }
}