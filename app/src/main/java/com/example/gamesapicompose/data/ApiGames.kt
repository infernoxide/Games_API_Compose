package com.example.gamesapicompose.data

import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.util.Constants.Companion.API_KEY
import com.example.gamesapicompose.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiGames {
    @GET(ENDPOINT+API_KEY)
    suspend fun getGames(): Response<GamesModel>
}