package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.domain.model.GamesModel
import com.example.gamesapicompose.domain.repository.GamesRepository
import javax.inject.Inject

class GetGamesByPagingUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): GamesModel {
        return repository.getGamesByPaging(page, pageSize)
    }
}