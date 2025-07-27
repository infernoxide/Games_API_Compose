package com.example.gamesapicompose.data.repository

import com.example.gamesapicompose.data.local.room.dao.GamesDAO
import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.data.mapper.toEntity
import com.example.gamesapicompose.data.remote.ApiGames
import com.example.gamesapicompose.di.NetworkMonitor
import com.example.gamesapicompose.domain.model.GameList
import com.example.gamesapicompose.domain.model.GamesModel
import com.example.gamesapicompose.domain.model.SingleGameModel
import com.example.gamesapicompose.domain.repository.GamesRepository
import java.net.UnknownHostException
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesDAO: GamesDAO,
    private val apiGames: ApiGames,
    private val networkMonitor: NetworkMonitor
) : GamesRepository {

    override suspend fun getGameByID(id: Int): DetailGame? {

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

    override suspend fun getGamesByPaging(page: Int, pageSize: Int): GamesModel {
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
            throw UnknownHostException()
        }

        val response = apiGames.getGamesByPaging(page, pageSize)
        val gamesToCache = response.results.map { it.toEntity(page) }
        gamesDAO.insertGamesHomeList(gamesToCache)

        return response
    }

    override suspend fun getGameByName(name:String) : SingleGameModel? {
        val response = apiGames.getGameByName(name)
        return if (response.isSuccessful) response.body() else null
    }

}