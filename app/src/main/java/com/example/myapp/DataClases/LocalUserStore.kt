package com.example.myapp.DataClases

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class LocalUserStore(private val context: Context) {
    companion object {
        val EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[EMAIL_KEY] }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun clearEmail() {
        context.dataStore.edit { it.remove(EMAIL_KEY) }
    }
}