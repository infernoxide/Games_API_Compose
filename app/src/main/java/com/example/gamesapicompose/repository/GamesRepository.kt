package com.example.gamesapicompose.repository

import com.example.gamesapicompose.data.ApiGames
import com.example.gamesapicompose.model.GameList
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getGames(): List<GameList>?{
        val response = apiGames.getGames()

        if (response.isSuccessful)
            return response.body()?.results

        return null
    }

}