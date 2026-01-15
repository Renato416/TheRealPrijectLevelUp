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
import com.example.therealprijectlevelup.data.SettingsStore
import com.example.therealprijectlevelup.ui.theme.TheRealPrijectLevelUpTheme
import com.example.therealprijectlevelup.viewModels.*
import com.example.therealprijectlevelup.views.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            // 1. DATABASE
            val settingsStore = remember { SettingsStore(context) }

            // 2. VIEWMODELS (CONECTADOS AL STORE)
            val settingsViewModel = remember { SettingsViewModel(settingsStore) }
            val loginViewModel = remember { LoginViewModel(settingsStore) }
            val registerViewModel = remember { RegisterViewModel(settingsStore) }

            val homeViewModel = remember { HomeViewModel() }
            val cartViewModel = remember { CartViewModel() }
            val chatViewModel = remember { ChatViewModel() }
            val profileViewModel = remember { ProfileViewModel() }

            // 3. ESTADOS GLOBALES
            val darkModeActive by settingsViewModel.isDarkMode.collectAsState()
            val userSession by settingsViewModel.userEmail.collectAsState()

            TheRealPrijectLevelUpTheme(darkTheme = darkModeActive) {

                // VARIABLE DE NAVEGACIÓN
                var currentScreen by rememberSaveable { mutableStateOf("login") }

                // 4. LÓGICA DE SESIÓN AUTOMÁTICA
                // Cada vez que 'userSession' cambie, decidimos a dónde ir
                LaunchedEffect(userSession) {
                    if (userSession.isNotEmpty()) {
                        // SI HAY USUARIO GUARDADO, VAMOS DIRECTO A HOME
                        currentScreen = "home"
                    } else {
                        // SI NO HAY USUARIO (O SE HIZO LOGOUT), VAMOS A LOGIN
                        currentScreen = "login"
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onRegisterClick = { currentScreen = "register" },
                            viewModel = loginViewModel
                        )
                        "register" -> RegisterScreen(
                            onBack = { currentScreen = "login" },
                            viewModel = registerViewModel
                        )
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
                            profileViewModel = profileViewModel
                        )
                    }
                }
            }
        }
    }
}