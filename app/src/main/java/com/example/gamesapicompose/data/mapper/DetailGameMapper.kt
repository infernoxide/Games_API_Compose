package com.example.gamesapicompose.data.mapper

import com.example.gamesapicompose.data.local.room.entities.DetailGame
import com.example.gamesapicompose.domain.model.SingleGameModel


fun SingleGameModel.toEntity(): DetailGame {
    return DetailGame(
        id = this.id.toLong(),
        name = this.name,
        description_raw = this.description_raw,
        metacritic = this.metacritic,
        website = this.website,
        background_image = this.background_image
    )
}