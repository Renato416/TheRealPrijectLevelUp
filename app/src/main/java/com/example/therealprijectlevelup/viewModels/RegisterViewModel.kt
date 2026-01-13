package com.example.therealprijectlevelup.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.therealprijectlevelup.models.User

class RegisterViewModel : ViewModel() {

    var user = mutableStateOf(User())
    var registerResult = mutableStateOf("")

    fun register() {
        val u = user.value

        registerResult.value = when {
            u.email.isBlank() || u.username.isBlank() ->
                "Datos incompletos"

            u.password.length < 6 ->
                "Contraseña muy corta"

            else -> "SUCCESS"
        }
    }
}
