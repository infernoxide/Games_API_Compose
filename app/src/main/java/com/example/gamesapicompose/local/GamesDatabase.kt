package com.example.gamesapicompose.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GamesHome::class, DetailGame::class], version = 1, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDAO(): GamesDAO
}