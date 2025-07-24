package com.example.gamesapicompose.domain.repository

import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.domain.model.GamesModel
import com.example.gamesapicompose.domain.model.SingleGameModel

interface GamesRepository {
    suspend fun getGameByID(id:Int):DetailGame?
    suspend fun getGamesByPaging(page: Int, pageSize: Int):GamesModel
    suspend fun getGameByName(name:String):SingleGameModel?
}