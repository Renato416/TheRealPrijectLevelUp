package com.example.therealprijectlevelup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.therealprijectlevelup.ui.theme.TheRealPrijectLevelUpTheme
import com.example.therealprijectlevelup.views.LoginScreen
import com.example.therealprijectlevelup.views.RegisterScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TheRealPrijectLevelUpTheme {

                var currentScreen by remember { mutableStateOf("login") }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->   // 👈 padding ignorado explícitamente

                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onRegisterClick = { currentScreen = "register" }
                        )

                        "register" -> RegisterScreen(
                            onBack = { currentScreen = "login" }
                        )
                    }
                }
            }
        }
    }
}
