package com.example.therealprijectlevelup.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therealprijectlevelup.viewModels.LoginViewModel

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val blueColor = Color(0xFF5877FF) // COLOR AZUL DE MARCA
    val roundedShape = RoundedCornerShape(12.dp) // BORDES REDONDEADOS COMUNES

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // HEADER: LOGO Y TÍTULO
        SimpleHeader(title = "Inicio de sesión")

        Spacer(modifier = Modifier.height(40.dp))

        // CAMPO: NOMBRE DE USUARIO
        OutlinedTextField(
            value = viewModel.username.value,
            onValueChange = { viewModel.username.value = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),
            shape = roundedShape,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO: CONTRASEÑA
        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = roundedShape,
            singleLine = true
        )

        // ENLACE: ¿OLVIDASTE TU CONTRASEÑA?
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextButton(onClick = { /* ACCIÓN OLVIDÉ CONTRASEÑA */ }) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = blueColor,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÓN: INGRESAR
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blueColor,
                contentColor = Color.White
            ),
            shape = roundedShape
        ) {
            Text("Ingresar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        // MENSAJE DE ERROR/CARGA
        if (viewModel.loginResult.value.isNotEmpty() && viewModel.loginResult.value != "SUCCESS") {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = viewModel.loginResult.value,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // EMPUJA EL CONTENIDO INFERIOR HACIA ABAJO

        // SECCIÓN: CREAR CUENTA
        Text(
            text = "¿No eres miembro?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blueColor,
                contentColor = Color.White
            ),
            shape = roundedShape
        ) {
            Text("Crear cuenta", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// COMPONENTE REUTILIZABLE PARA EL HEADER SIMPLE
@Composable
fun SimpleHeader(title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // SIMULACIÓN DEL LOGO "LevelUP" CON COLORES
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Level",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = "UP",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF00C897) // COLOR VERDE DEL LOGO
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}