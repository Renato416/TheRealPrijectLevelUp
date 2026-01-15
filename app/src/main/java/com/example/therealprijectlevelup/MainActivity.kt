package com.example.therealprijectlevelup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therealprijectlevelup.data.SettingsStore
import com.example.therealprijectlevelup.ui.theme.TheRealPrijectLevelUpTheme
import com.example.therealprijectlevelup.viewModels.SettingsViewModel
import com.example.therealprijectlevelup.viewModels.HomeViewModel
import com.example.therealprijectlevelup.viewModels.CartViewModel
import com.example.therealprijectlevelup.viewModels.ChatViewModel
import com.example.therealprijectlevelup.viewModels.ProfileViewModel
import com.example.therealprijectlevelup.views.CartView
import com.example.therealprijectlevelup.views.HomeView
import com.example.therealprijectlevelup.views.ProfileView
import com.example.therealprijectlevelup.views.ChatView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            // 1. INICIALIZACIÓN DE LOS COMPONENTES DE PERSISTENCIA Y ESTADO GLOBAL
            val settingsStore = remember { SettingsStore(context) }
            val settingsViewModel = remember { SettingsViewModel(settingsStore) }

            // 2. INICIALIZACIÓN DE VIEWMODELS ESPECÍFICOS DE CADA VISTA
            // SE UTILIZA EL DELEGADO viewModel() PARA QUE ANDROID GESTIONE SU CICLO DE VIDA
            val homeViewModel: HomeViewModel = viewModel()
            val cartViewModel: CartViewModel = viewModel()
            val chatViewModel: ChatViewModel = viewModel()
            val profileViewModel: ProfileViewModel = viewModel()

            // 3. OBSERVACIÓN DEL MODO OSCURO
            val darkModeActive by settingsViewModel.isDarkMode.collectAsState()

            TheRealPrijectLevelUpTheme(darkTheme = darkModeActive) {
                var currentScreen by rememberSaveable { mutableStateOf("home") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 4. NAVEGACIÓN CON PARÁMETROS NOMBRADOS EXACTOS SEGÚN CADA VISTA
                    when (currentScreen) {
                        "home" -> HomeView(
                            onNavigate = { currentScreen = it },
                            homeViewModel = homeViewModel,
                            settingsViewModel = settingsViewModel
                        )
                        "cart" -> CartView(
                            onNavigate = { currentScreen = it },
                            settingsViewModel = settingsViewModel,
                            cartViewModel = cartViewModel
                        )
                        "messages" -> ChatView(
                            onNavigate = { currentScreen = it },
                            settingsViewModel = settingsViewModel,
                            chatViewModel = chatViewModel
                        )
                        "profile" -> ProfileView(
                            onNavigate = { currentScreen = it },
                            viewModel = settingsViewModel,
                            profileViewModel = profileViewModel // PASAR EL NUEVO VIEWMODEL
                        )
                    }
                }
            }
        }
    }
}