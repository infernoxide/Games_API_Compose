package com.example.gamesapicompose.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamesapicompose.data.local.room.dao.GamesDAO
import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.data.local.room.entities.GamesHome

@Database(entities = [GamesHome::class, DetailGame::class], version = 1, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDAO(): GamesDAO
}