package com.example.therealprijectlevelup.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therealprijectlevelup.data.SettingsStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var loginResult = mutableStateOf("")

    fun login() {
        viewModelScope.launch {
            loginResult.value = "Cargando..."
            delay(1200)

            if (username.value.isBlank() || password.value.isBlank()) {
                loginResult.value = "Campos vacíos"
                return@launch
            }

            // 1. OBTENEMOS EL USUARIO Y CONTRASEÑA REALES GUARDADOS
            val savedUser = settingsStore.getStoredUser.first()
            val savedPass = settingsStore.getStoredPass.first()

            // 2. COMPARAMOS LO QUE ESCRIBIÓ EL USUARIO CON LO GUARDADO
            if (username.value == savedUser && password.value == savedPass) {
                // ÉXITO: GUARDAMOS LA SESIÓN ACTIVA
                settingsStore.saveEmail(username.value)
                loginResult.value = "SUCCESS"
            } else {
                loginResult.value = "Credenciales incorrectas"
            }
        }
    }
}