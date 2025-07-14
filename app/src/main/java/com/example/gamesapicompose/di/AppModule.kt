package com.example.gamesapicompose.di

import android.content.Context
import androidx.room.Room
import com.example.gamesapicompose.data.ApiGames
import com.example.gamesapicompose.local.GamesDAO
import com.example.gamesapicompose.local.GamesDatabase
import com.example.gamesapicompose.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAPIGames(retrofit: Retrofit): ApiGames {
        return retrofit.create(ApiGames::class.java)

    }

    @Singleton
    @Provides
    fun providesGamesDAO(gamesDatabase: GamesDatabase) : GamesDAO {
        return gamesDatabase.gamesDAO()
    }

    @Singleton
    @Provides
    fun providesGamesDataBase(@ApplicationContext context: Context) : GamesDatabase {
        return Room.databaseBuilder(
            context,
            GamesDatabase::class.java,
            "games_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

}