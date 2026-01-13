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
import com.example.therealprijectlevelup.ui.theme.TheRealPrijectLevelUpTheme
import com.example.therealprijectlevelup.views.CartView
import com.example.therealprijectlevelup.views.HomeView
import com.example.therealprijectlevelup.views.ProfileView
import com.example.therealprijectlevelup.views.CustomerServiceView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheRealPrijectLevelUpTheme {
                // ESTADO QUE CONTROLA LA NAVEGACIÓN ACTUAL
                var currentScreen by rememberSaveable { mutableStateOf("home") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // LÓGICA DE CONMUTACIÓN DE VISTAS (NAVEGACIÓN)
                    // CORRECCIÓN: TODAS LAS OPCIONES ESTÁN AHORA DENTRO DEL BLOQUE WHEN
                    when (currentScreen) {
                        "home" -> HomeView(onNavigate = { currentScreen = it })
                        "profile" -> ProfileView(onNavigate = { currentScreen = it })
                        "cart" -> CartView(onNavigate = { currentScreen = it })
                        "messages" -> CustomerServiceView(onNavigate = { currentScreen = it })
                    }
                }
            }
        }
    }
}