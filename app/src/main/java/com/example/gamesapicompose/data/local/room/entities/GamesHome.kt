package com.example.gamesapicompose.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GamesHome")
data class GamesHome(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "background_image")
    val background_image : String,
    @ColumnInfo(name = "page")
    val page: Int = 0
)
