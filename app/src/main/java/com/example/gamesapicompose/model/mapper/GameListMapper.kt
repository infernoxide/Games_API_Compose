package com.example.gamesapicompose.model.mapper

import com.example.gamesapicompose.local.GamesHome
import com.example.gamesapicompose.model.GameList

fun GameList.toEntity(page:Int): GamesHome {
    return GamesHome(
        id = this.id?.toLong() ?: 0L,
        name = this.name.orEmpty(),
        background_image = this.background_image.orEmpty(),
        page = page
    )
}