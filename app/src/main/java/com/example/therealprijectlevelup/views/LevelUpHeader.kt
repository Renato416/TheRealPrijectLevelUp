package com.example.therealprijectlevelup.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode // IMPORTANTE: ICONO LUNA
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny // IMPORTANTE: ICONO SOL
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therealprijectlevelup.viewModels.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelUpHeader(
    title: String,
    viewModel: SettingsViewModel
) {
    val isDark by viewModel.isDarkMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5877FF)) // AZUL DE MARCA
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                placeholder = {
                    Text(
                        "Buscar...",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            // --- AQUÍ ESTÁ EL CAMBIO SOLICITADO ---
            IconButton(
                onClick = {
                    viewModel.toggleDarkMode(!isDark)
                },
                modifier = Modifier
                    .size(50.dp) // TAMAÑO PARA QUE QUEDE ALINEADO CON LA BARRA
                // OPCIONAL: FONDO CIRCULAR SUTIL SI LO QUIERES
                // .background(Color.Black.copy(alpha = 0.1f), CircleShape)
            ) {
                // LÓGICA VISUAL:
                // SI ESTÁ OSCURO (isDark = true) -> MOSTRAMOS SOL (WbSunny)
                // SI ESTÁ CLARO (isDark = false) -> MOSTRAMOS LUNA (DarkMode)
                Icon(
                    imageVector = if (isDark) Icons.Default.WbSunny else Icons.Default.DarkMode,
                    contentDescription = "Cambiar Tema",
                    tint = Color.White, // ICONO BLANCO SOBRE FONDO AZUL
                    modifier = Modifier.size(32.dp) // TAMAÑO DEL ICONO INTERNO
                )
            }
        }
    }
}