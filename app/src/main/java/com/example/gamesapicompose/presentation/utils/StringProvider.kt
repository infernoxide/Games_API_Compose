package com.example.gamesapicompose.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringProvider {
    fun getString(@StringRes id: Int, vararg args: Any): String
}

class DefaultStringProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : StringProvider {
    override fun getString(id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }
}