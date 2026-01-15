package com.example.therealprijectlevelup.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LevelUpBottomNavigation(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    NavigationBar(
        // EL CONTENEDOR SE MANTIENE ADAPTABLE (BLANCO/GRIS SEGÚN MODO)
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        // DEFINIMOS LOS COLORES PERSONALIZADOS
        val navColors = NavigationBarItemDefaults.colors(
            // 1. FONDO DEL ÍTEM SELECCIONADO: AZUL HEADER
            indicatorColor = Color(0xFF5877FF),

            // 2. ICONO SELECCIONADO: BLANCO (PARA CONTRASTE CON EL AZUL)
            selectedIconColor = Color.White,

            // 3. ICONO NO SELECCIONADO: GRIS ADAPTABLE
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // ITEM: HOME
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            selected = selectedTab == "home",
            onClick = { onTabSelected("home") },
            colors = navColors
        )

        // ITEM: CART
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            selected = selectedTab == "cart",
            onClick = { onTabSelected("cart") },
            colors = navColors
        )

        // ITEM: MESSAGES
        NavigationBarItem(
            icon = { Icon(Icons.Default.Email, contentDescription = null) },
            selected = selectedTab == "messages",
            onClick = { onTabSelected("messages") },
            colors = navColors
        )

        // ITEM: PERFIL
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            selected = selectedTab == "profile",
            onClick = { onTabSelected("profile") },
            colors = navColors
        )
    }
}