package com.example.gamesapicompose.repository

import com.example.gamesapicompose.data.ApiGames
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.model.SingleGameModel
import okio.IOException
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val apiGames: ApiGames,
    private val networkMonitor: NetworkMonitor
) {
    suspend fun getGameByID(id:Int) : SingleGameModel? {
        val response = apiGames.getGameByID(id)

        if (response.isSuccessful)
            return response.body()

        return null
    }

    suspend fun getGamesByPaging(page: Int, pageSize: Int): GamesModel {
        if (!networkMonitor.isConnected.value) {
            throw IOException("Sin conexión a internet")
        }
        return apiGames.getGamesByPaging(page, pageSize)
    }

    suspend fun getGameByName(name:String) : SingleGameModel? {
        val response = apiGames.getGameByName(name)

        if (response.isSuccessful)
            return response.body()

        return null
    }

}