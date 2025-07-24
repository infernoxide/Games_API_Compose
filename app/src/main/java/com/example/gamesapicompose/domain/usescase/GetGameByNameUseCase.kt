package com.example.gamesapicompose.domain.usescase

import com.example.gamesapicompose.domain.model.SingleGameModel
import com.example.gamesapicompose.domain.repository.GamesRepository
import javax.inject.Inject

class GetGameByNameUseCase @Inject constructor(
    private val repository: GamesRepository
) {
    suspend operator fun invoke (name:String):SingleGameModel? {
        return repository.getGameByName(name)
    }
}