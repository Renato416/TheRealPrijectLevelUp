package com.example.therealprijectlevelup.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therealprijectlevelup.data.SettingsStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = settingsStore.isDarkMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // ESTADO DE SESIÓN (SI ESTÁ VACÍO, NO HAY USUARIO)
    val userEmail: StateFlow<String> = settingsStore.getEmail
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch { settingsStore.saveDarkMode(enabled) }
    }

    fun logout() {
        viewModelScope.launch {
            settingsStore.saveEmail("") // BORRAMOS EL USUARIO
        }
    }
}