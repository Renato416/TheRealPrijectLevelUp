package com.example.therealprijectlevelup.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")

class SettingsStore(private val context: Context) {

    companion object {
        val USER_EMAIL_KEY = stringPreferencesKey("user_email") // ESTO ES LA SESIÓN ACTIVA
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")

        // --- NUEVAS CLAVES PARA GUARDAR EL USUARIO REGISTRADO ---
        val STORED_USER_KEY = stringPreferencesKey("stored_username")
        val STORED_PASS_KEY = stringPreferencesKey("stored_password")
    }

    // FLUJO DE SESIÓN (SI ESTÁ VACÍO, NO HAY LOGIN)
    val getEmail: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL_KEY] ?: ""
    }

    // FLUJO MODO OSCURO
    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DARK_MODE_KEY] ?: false
    }

    // --- NUEVOS FLUJOS PARA LEER EL USUARIO REGISTRADO ---
    // Si no hay nadie registrado, usamos "Renato416" como default para pruebas
    val getStoredUser: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STORED_USER_KEY] ?: "Renato416"
    }

    val getStoredPass: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[STORED_PASS_KEY] ?: "123456"
    }

    // --- FUNCIONES DE GUARDADO ---

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    suspend fun saveDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    suspend fun saveCredentials(user: String, pass: String) {
        context.dataStore.edit { preferences ->
            preferences[STORED_USER_KEY] = user
            preferences[STORED_PASS_KEY] = pass
        }
    }
}