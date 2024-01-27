package com.blue.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStoreDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): DataStoreDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun tokenFlow(): Flow<String> {
        val key = stringPreferencesKey("auth")
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }
    }

    suspend fun updateToken(token: String){
        val key = stringPreferencesKey("auth")
        context.dataStore.edit { settings->
            settings[key] = token
        }
    }

    fun getInitState(): Flow<Boolean>{
        val key = booleanPreferencesKey("init")
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: false
            }
    }

    suspend fun updateInitState(state: Boolean){
        val key = booleanPreferencesKey("init")
        context.dataStore.edit {settings ->
            settings[key] = state
        }
    }
}
