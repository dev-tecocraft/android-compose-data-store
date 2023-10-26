package com.teco.composwithdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("app_data_store")

class DataSharedPref(private val context: Context) {
    private object PreferenceKeys{
        val keyCurrentCounter = intPreferencesKey("current_counter")
        val keyIsUserLogin = booleanPreferencesKey("is_user_login")
    }

    suspend fun updateUserLogin(isUserLogin: Boolean) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.keyIsUserLogin] = isUserLogin
    }

    fun isUserLogin() = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.keyIsUserLogin] ?: false
    }

    suspend fun updateCounter(counter: Int) = context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.keyCurrentCounter] = counter
    }

    fun currentCount() = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.keyCurrentCounter] ?: 0
    }
}