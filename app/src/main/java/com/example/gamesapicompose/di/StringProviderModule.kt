package com.example.gamesapicompose.di

import android.content.Context
import com.example.gamesapicompose.presentation.utils.DefaultStringProvider
import com.example.gamesapicompose.presentation.utils.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object StringProviderModule {

    @Provides
    fun provideStringProvider(
        @ApplicationContext context: Context
    ): StringProvider = DefaultStringProvider(context)
}