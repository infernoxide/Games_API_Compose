package com.example.gamesapicompose.repository

import com.example.gamesapicompose.data.ApiGames
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.local.DetailGame
import com.example.gamesapicompose.local.GamesDAO
import com.example.gamesapicompose.model.GameList
import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.model.SingleGameModel
import com.example.gamesapicompose.model.mapper.toEntity
import okio.IOException
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val gamesDAO: GamesDAO,
    private val apiGames: ApiGames,
    private val networkMonitor: NetworkMonitor
) {
    suspend fun getGameByID(id: Int): DetailGame? {

        val localGame = gamesDAO.getDetailGame(id.toLong())

        if (localGame != null) return localGame
        if (!networkMonitor.isConnected.value) return null

        val response = apiGames.getGameByID(id)
        if (response.isSuccessful) {
            val remote = response.body()
            if (remote != null) {
                val entity = remote.toEntity()
                gamesDAO.insertDetailGame(entity)
                return entity
            }
        }

        return null
    }

    suspend fun getGamesByPaging(page: Int, pageSize: Int): GamesModel {
        val localGames = gamesDAO.getGamesHomeByPage(page)

        if (localGames.isNotEmpty()) {
            return GamesModel(
                count = localGames.size,
                results = localGames.map {
                    GameList(
                        id = it.id.toInt(),
                        name = it.name,
                        background_image = it.background_image
                    )
                }
            )
        }

        if (!networkMonitor.isConnected.value) {
            throw IOException("Sin conexión a internet")
        }

        val response = apiGames.getGamesByPaging(page, pageSize)
        val gamesToCache = response.results.map { it.toEntity(page) }
        gamesDAO.insertGamesHomeList(gamesToCache)

        return response
    }

    suspend fun getGameByName(name:String) : SingleGameModel? {
        val response = apiGames.getGameByName(name)
        return if (response.isSuccessful) response.body() else null
    }

}