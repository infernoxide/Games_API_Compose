package com.example.gamesapicompose.model.mapper

import com.example.gamesapicompose.local.DetailGame
import com.example.gamesapicompose.model.SingleGameModel


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