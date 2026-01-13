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
    onTabSelected: (String) -> Unit // FUNCIÓN PARA CAMBIAR DE VISTA
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        // ITEM: HOME
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            selected = selectedTab == "home",
            onClick = { onTabSelected("home") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFE0E0E0))
        )


        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            selected = selectedTab == "cart",
            onClick = { onTabSelected("cart") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFE0E0E0))
        )



        NavigationBarItem(
            icon = { Icon(Icons.Default.Email, contentDescription = null) },
            selected = selectedTab == "messages",
            onClick = { onTabSelected("messages") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFE0E0E0))
        )


        // ITEM: PERFIL
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            selected = selectedTab == "profile",
            onClick = { onTabSelected("profile") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFFE0E0E0))
        )
    }
}