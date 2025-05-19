package ru.tinkoff.lunch.network.common.user_info

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserInfoManager {
    suspend fun saveUserId(id: String)
    suspend fun getUserId(): String?
    suspend fun clearUserId()
}

class UserInfoDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserInfoManager {

    private val idKey = stringPreferencesKey("user_id")

    override suspend fun saveUserId(id: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[idKey] = id
        }
    }

    override suspend fun getUserId(): String? {
        return dataStore.data.map { preferences ->
            preferences[idKey]
        }.first()
    }

    override suspend fun clearUserId() {
        dataStore.edit { it.remove(idKey) }
    }
}
