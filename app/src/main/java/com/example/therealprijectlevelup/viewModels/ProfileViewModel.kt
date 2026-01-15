package com.example.therealprijectlevelup.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserProfile(
    val username: String,
    val address: String,
    val email: String,
    val phone: String,
    val birthDate: String
)

class ProfileViewModel : ViewModel() {

    // ESTADO DEL PERFIL DE USUARIO
    private val _userProfile = MutableStateFlow(
        UserProfile(
            username = "Renato416",
            address = "Casablanca, Valparaíso",
            email = "re.rojasc@duocuc.cl",
            phone = "9 3094 5360",
            birthDate = "19 - 07 - 2005"
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    // AQUÍ IRÍAN FUNCIONES COMO updateProfile(), logout(), ETC.
}