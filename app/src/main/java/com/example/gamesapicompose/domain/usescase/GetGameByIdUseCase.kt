package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.domain.repository.GamesRepository
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke (id: Int) : DetailGame? {
        return repository.getGameByID(id)
    }
}