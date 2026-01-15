package com.example.therealprijectlevelup.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
            .background(Color(0xFF5877FF)) // MANTENEMOS EL AZUL DE MARCA
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White, // TEXTO SIEMPRE BLANCO SOBRE FONDO AZUL
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
            // CAMPO DE BÚSQUEDA ADAPTABLE
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
                    // AQUÍ ESTÁ LA MAGIA: USAMOS 'surface' EN LUGAR DE 'Color.White'
                    // MODO CLARO -> BLANCO | MODO OSCURO -> GRIS OSCURO
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,

                    // EL TEXTO SE ADAPTA (NEGRO O BLANCO)
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

                    // QUITAMOS EL BORDE POR DEFECTO PARA QUE SE VEA LIMPIO
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            Button(
                onClick = {
                    viewModel.toggleDarkMode(!isDark)
                },
                modifier = Modifier.height(50.dp),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    // INTERCAMBIO DE COLORES PARA FEEDBACK VISUAL
                    containerColor = if (isDark) Color.White else Color.Black
                )
            ) {
                Text(
                    text = if (isDark) "LUZ" else "MODO",
                    fontSize = 12.sp,
                    color = if (isDark) Color.Black else Color.White
                )
            }
        }
    }
}