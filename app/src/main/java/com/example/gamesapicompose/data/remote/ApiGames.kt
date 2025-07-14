package com.example.gamesapicompose.data.remote

import com.example.gamesapicompose.BuildConfig
import com.example.gamesapicompose.domain.model.GamesModel
import com.example.gamesapicompose.domain.model.SingleGameModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGames {

    @GET("games/{id}")
    suspend fun getGameByID(
        @Path("id") id: Int,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<SingleGameModel>

    @GET("games")
    suspend fun getGamesByPaging(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): GamesModel

    @GET("games/{name}")
    suspend fun getGameByName(
        @Path("name") name: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<SingleGameModel>
    
}