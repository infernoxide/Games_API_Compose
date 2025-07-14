package com.example.gamesapicompose.domain.model

data class SingleGameModel(
    val id:Int,
    val name: String,
    val description_raw: String,
    val metacritic: Int,
    val website: String,
    val background_image: String
)
