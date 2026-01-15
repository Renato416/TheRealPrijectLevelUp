package com.example.therealprijectlevelup.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therealprijectlevelup.models.Product
import com.example.therealprijectlevelup.viewModels.HomeViewModel
import com.example.therealprijectlevelup.viewModels.SettingsViewModel

@Composable
fun HomeView(
    onNavigate: (String) -> Unit,
    homeViewModel: HomeViewModel,
    settingsViewModel: SettingsViewModel
) {
    val products = homeViewModel.products.value

    Scaffold(
        // EL FONDO REACCIONARÁ AUTOMÁTICAMENTE AL TEMA (BLANCO O NEGRO)
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            LevelUpHeader("Level UP", settingsViewModel)
        },
        bottomBar = {
            LevelUpBottomNavigation("home", onNavigate)
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    var visible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.9f,
        label = "scaleAnimation"
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = scale
            },
        // AQUI ESTA EL CAMBIO CLAVE PARA EL MODO OSCURO:
        // USAMOS 'outlineVariant' QUE ES GRIS EN MODO CLARO Y GRIS CLARO EN MODO OSCURO
        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(
            // 'surface' ES BLANCO EN MODO CLARO Y GRIS OSCURO EN MODO OSCURO
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(120.dp),
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // EL TEXTO DEL ICONO AHORA USA 'onSurface' PARA SER VISIBLE EN AMBOS MODOS
                    Text("IMG", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // NOMBRE DEL PRODUCTO
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                // QUITAMOS 'Color.Black'. USAMOS EL COLOR DEL TEMA POR DEFECTO
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // PRECIO DEL PRODUCTO
            Text(
                text = "$ ${product.price}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal,
                // QUITAMOS 'Color.Black'. EL SISTEMA ELEGIRÁ BLANCO O NEGRO SEGÚN EL MODO
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // BOTÓN COMPRAR
            Button(
                onClick = { /* LÓGICA DE COMPRA */ },
                colors = ButtonDefaults.buttonColors(
                    // ESTE AZUL SE MANTIENE PORQUE ES COLOR DE MARCA (BRANDING)
                    containerColor = Color(0xFF5877FF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                Text(
                    text = "Comprar",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}