package com.blue.datastore.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserAuthHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun authFlow(): Flow<String> {
        val authKey = stringPreferencesKey("auth")
        val exampleCounterFlow: Flow<String> = context.dataStore.data
            .map { preferences ->
                preferences[authKey] ?: ""
            }

        return exampleCounterFlow
    }

    suspend fun updateAuth(token: String){
        val authKey = stringPreferencesKey("auth")
        context.dataStore.edit { settings->
            settings[authKey] = token
        }
    }
}