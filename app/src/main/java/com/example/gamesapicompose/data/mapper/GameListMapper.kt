package com.example.gamesapicompose.data.mapper

import com.example.gamesapicompose.data.local.room.entities.GamesHome
import com.example.gamesapicompose.domain.model.GameList

fun GameList.toEntity(page:Int): GamesHome {
    return GamesHome(
        id = this.id?.toLong() ?: 0L,
        name = this.name.orEmpty(),
        background_image = this.background_image.orEmpty(),
        page = page
    )
}