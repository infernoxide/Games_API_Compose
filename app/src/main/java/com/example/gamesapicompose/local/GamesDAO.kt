package com.example.gamesapicompose.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GamesDAO {

    //Games home
    @Query("SELECT * FROM GamesHome WHERE page = :page ORDER BY id ASC")
    suspend fun getGamesHomeByPage(page: Int): List<GamesHome>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGamesHomeList(gamesHome: List<GamesHome>)

    @Query("DELETE FROM GamesHome")
    suspend fun clearGamesHome()

    //Detail game
    @Query("SELECT * FROM DetailGame WHERE id = :id")
    suspend fun getDetailGame(id: Long): DetailGame?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(detailGame: DetailGame)

    @Query("DELETE FROM DetailGame")
    suspend fun clearDetailGame()
}