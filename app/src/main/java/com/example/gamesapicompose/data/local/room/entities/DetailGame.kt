package com.example.gamesapicompose.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DetailGame")
data class DetailGame(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description_raw")
    val description_raw: String,
    @ColumnInfo(name = "metacritic")
    val metacritic: Int,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "background_image")
    val background_image: String
)
