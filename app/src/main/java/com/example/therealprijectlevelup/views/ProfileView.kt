package com.example.therealprijectlevelup.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therealprijectlevelup.viewModels.ProfileViewModel
import com.example.therealprijectlevelup.viewModels.SettingsViewModel

@Composable
fun ProfileView(
    onNavigate: (String) -> Unit,
    viewModel: SettingsViewModel,
    profileViewModel: ProfileViewModel
) {
    val userProfile by profileViewModel.userProfile.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background, // ADAPTABLE AL MODO OSCURO
        topBar = { LevelUpHeader("Level UP", viewModel) },
        bottomBar = { LevelUpBottomNavigation("profile", onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // PERMITIR SCROLL
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TARJETA CONTENEDORA PRINCIPAL
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 1. ICONO DE PERFIL CIRCULAR
                    Surface(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxSize(),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 2. NOMBRE DE USUARIO
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = userProfile.username,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. CAMPOS DE INFORMACIÓN
                    ProfileField(
                        label = "Dirección",
                        value = userProfile.address,
                        icon = Icons.Default.LocationOn
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileField(
                        label = "Correo electrónico",
                        value = userProfile.email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileField(
                        label = "Numero de teléfono",
                        value = userProfile.phone
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileField(
                        label = "Edad",
                        value = userProfile.birthDate
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // LINK DE AYUDA
                    TextButton(onClick = { /* ACCIÓN AYUDA */ }) {
                        Text(
                            text = "¿Necesito ayuda?",
                            color = Color(0xFF5877FF),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // --- AQUÍ ESTÁ EL NUEVO BOTÓN DE LOGOUT ---
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // ACCIÓN: BORRAR SESIÓN Y VOLVER AL LOGIN
                    viewModel.logout()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // BUENA ALTURA TÁCTIL
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F), // ROJO PARA INDICAR "SALIR"
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Cerrar Sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // ESPACIO EXTRA AL FINAL PARA QUE EL SCROLL NO QUEDE APRETADO
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// COMPONENTE REUTILIZABLE (SIN CAMBIOS)
@Composable
fun ProfileField(
    label: String,
    value: String,
    icon: ImageVector? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = {}, // READ ONLY
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = if (icon != null) {
                { Icon(icon, contentDescription = null) }
            } else null,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            singleLine = true
        )
    }
}