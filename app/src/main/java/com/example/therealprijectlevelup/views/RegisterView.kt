package com.example.therealprijectlevelup.views


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therealprijectlevelup.viewModels.RegisterViewModel
import com.example.therealprijectlevelup.models.User
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val user = viewModel.user.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Registro", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user.email,
            onValueChange = { viewModel.user.value = user.copy(email = it) },
            label = { Text("Correo electrónico") }
        )

        OutlinedTextField(
            value = user.username,
            onValueChange = { viewModel.user.value = user.copy(username = it) },
            label = { Text("Nombre de usuario") }
        )

        OutlinedTextField(
            value = user.address,
            onValueChange = { viewModel.user.value = user.copy(address = it) },
            label = { Text("Dirección") }
        )

        OutlinedTextField(
            value = user.birthDate,
            onValueChange = { viewModel.user.value = user.copy(birthDate = it) },
            label = { Text("Fecha de nacimiento") }
        )

        OutlinedTextField(
            value = user.phone,
            onValueChange = { viewModel.user.value = user.copy(phone = it) },
            label = { Text("Teléfono") }
        )

        OutlinedTextField(
            value = user.password,
            onValueChange = { viewModel.user.value = user.copy(password = it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.register() }) {
            Text("Registrar")
        }

        if (viewModel.registerResult.value == "SUCCESS") {
            onBack()
        }

        if (viewModel.registerResult.value.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(viewModel.registerResult.value)
        }
    }
}

