package com.example.gamesapicompose.model

data class GamesModel(
    val count:Int? = 0,
    val list:List<GameList>?= emptyList()
)
