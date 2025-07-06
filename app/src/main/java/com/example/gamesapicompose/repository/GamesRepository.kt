package com.example.gamesapicompose.repository

import com.example.gamesapicompose.data.ApiGames
import com.example.gamesapicompose.model.GameList
import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.model.SingleGameModel
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getGames(): List<GameList>?{
        val response = apiGames.getGames()

        if (response.isSuccessful)
            return response.body()?.results

        return null
    }

    suspend fun getGameByID(id:Int) : SingleGameModel? {
        val response = apiGames.getGameByID(id)

        if (response.isSuccessful)
            return response.body()

        return null
    }

    suspend fun getGamesByPaging(page: Int, pageSize: Int): GamesModel {
        return apiGames.getGamesByPaging(page, pageSize)
    }

}