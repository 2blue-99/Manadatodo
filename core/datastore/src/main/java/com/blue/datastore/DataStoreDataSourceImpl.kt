package com.blue.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreDataSource {

    override fun tokenFlow(): Flow<String> {
        val key = stringPreferencesKey("auth")
        return dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }
    }

    override suspend fun updateToken(token: String){
        val key = stringPreferencesKey("auth")
        dataStore.edit { settings->
            settings[key] = token
        }
    }

    override suspend fun getLastUpdateDateTime(): String {
        val key = stringPreferencesKey("init")
        return dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }.first()
    }

    override suspend fun updateLastUpdateTime(state: Boolean){
        val key = booleanPreferencesKey("init")
        dataStore.edit {settings ->
            settings[key] = state
        }
    }
}
