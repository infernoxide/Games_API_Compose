package com.example.gamesapicompose.data

import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.model.SingleGameModel
import com.example.gamesapicompose.util.Constants.Companion.API_KEY
import com.example.gamesapicompose.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGames {
    @GET(ENDPOINT+API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameByID(@Path(value = "id") id: Int): Response<SingleGameModel>

}