package com.example.therealprijectlevelup.views

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therealprijectlevelup.models.CartItem
import com.example.therealprijectlevelup.viewModels.CartViewModel
import com.example.therealprijectlevelup.viewModels.SettingsViewModel

@Composable
fun CartView(
    onNavigate: (String) -> Unit,
    settingsViewModel: SettingsViewModel,
    cartViewModel: CartViewModel
) {
    val cartItems by cartViewModel.cartItems.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background, // FONDO ADAPTABLE
        topBar = { LevelUpHeader("Level UP", settingsViewModel) },
        bottomBar = { LevelUpBottomNavigation("cart", onNavigate) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // LISTA DE PRODUCTOS (OCUPA EL ESPACIO DISPONIBLE)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartItems) { item ->
                    CartItemRow(item)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SECCIÓN INFERIOR: TOTAL A LA IZQUIERDA, BOTÓN A LA DERECHA
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // EMPUJA ELEMENTOS A LOS EXTREMOS
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Total: ",
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "$122.376",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal, // EN TU IMAGEN EL NUMERO NO ES NEGRITA
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Button(
                    onClick = { /* LÓGICA DE PAGO */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5877FF),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp), // BOTÓN CON BORDES REDONDEADOS
                    modifier = Modifier
                        .height(50.dp)
                        .width(120.dp) // ANCHO FIJO PARA QUE SE VEA COMO LA IMAGEN
                ) {
                    Text("Pagar", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(300)
        ) + fadeIn()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            // BORDE FINO Y ADAPTABLE (GRIS EN DARK MODE, NEGRO/GRIS EN LIGHT MODE)
            border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(16.dp) // CURVA SUAVE COMO EN LA FOTO
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. IMAGEN (PLACEHOLDER)
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Transparent // TRANSPARENTE
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        // AQUI IRÍA TU IMAGEN REAL. USAMOS UN ICONO O TEXTO POR AHORA
                        Text("IMG", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 2. DETALLES (TEXTOS)
                Column(
                    modifier = Modifier.weight(1f) // PARA QUE OCUPE EL RESTO DEL ANCHO
                ) {
                    // NOMBRE DEL PRODUCTO
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2 // POR SI EL NOMBRE ES LARGO
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // FILA DE CANTIDAD Y PRECIO
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween // PRECIO A LA DERECHA
                    ) {
                        Text(
                            text = "${item.quantity} u.",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "$${item.price}",
                            fontSize = 22.sp, // PRECIO GRANDE
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}