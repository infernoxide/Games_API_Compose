package com.example.gamesapicompose.data

import com.example.gamesapicompose.model.GamesModel
import com.example.gamesapicompose.model.SingleGameModel
import com.example.gamesapicompose.util.Constants.Companion.API_KEY
import com.example.gamesapicompose.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGames {
    @GET(ENDPOINT+API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameByID(@Path(value = "id") id: Int): Response<SingleGameModel>

    @GET(ENDPOINT+API_KEY)
    suspend fun getGamesByPaging(@Query("page") page:Int, @Query("page_size") pageSize:Int) : GamesModel

    @GET("$ENDPOINT/{name}$API_KEY")
    suspend fun getGameByName(@Path(value = "name") id: String): Response<SingleGameModel>

}