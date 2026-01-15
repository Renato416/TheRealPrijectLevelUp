package com.example.therealprijectlevelup.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therealprijectlevelup.data.SettingsStore
import com.example.therealprijectlevelup.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    var user = mutableStateOf(User())
    var registerResult = mutableStateOf("")

    fun register() {
        viewModelScope.launch {
            registerResult.value = "Registrando..."
            delay(1500)

            val u = user.value
            when {
                u.email.isBlank() || u.username.isBlank() ->
                    registerResult.value = "Datos incompletos"

                u.password.length < 6 ->
                    registerResult.value = "Contraseña muy corta"

                else -> {
                    // 1. GUARDAMOS LAS CREDENCIALES PARA FUTUROS LOGINS
                    settingsStore.saveCredentials(u.username, u.password)

                    // 2. INICIAMOS LA SESIÓN AUTOMÁTICAMENTE
                    settingsStore.saveEmail(u.username)

                    registerResult.value = "SUCCESS"
                }
            }
        }
    }
}