package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.domain.model.GamesModel
import com.example.gamesapicompose.domain.repository.GamesRepository
import junit.framework.TestCase.assertEquals
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

@ExperimentalCoroutinesApi
class GetGamesByPagingUseCaseTest {

    private lateinit var useCase: GetGamesByPagingUseCase
    private val repository: GamesRepository = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetGamesByPagingUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return result from repository`() = runTest {
        val page = 1
        val pageSize = 20
        val expectedResult = mock<GamesModel>()

        whenever(repository.getGamesByPaging(page, pageSize)).thenReturn(expectedResult)

        val result = useCase(page, pageSize)

        assertEquals(expectedResult, result)
        verify(repository).getGamesByPaging(page, pageSize)
    }
}