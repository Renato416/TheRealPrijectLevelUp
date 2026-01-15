package com.example.therealprijectlevelup.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        // MANTENEMOS EL BORDE ADAPTABLE PARA QUE SE VEA BIEN EN AMBOS MODOS
        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(
            // CAMBIO 1: FONDO SIEMPRE BLANCO PARA COMBINAR CON LA IMAGEN JPEG
            containerColor = Color.White
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
            // IMAGEN DEL PRODUCTO
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(12.dp))

            // NOMBRE DEL PRODUCTO
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                // CAMBIO 2: TEXTO SIEMPRE NEGRO (PARA QUE SE VEA SOBRE BLANCO)
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // PRECIO DEL PRODUCTO
            Text(
                text = "$ ${product.price}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal,
                // CAMBIO 3: TEXTO SIEMPRE NEGRO
                color = Color.Black,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // BOTÓN COMPRAR
            Button(
                onClick = { /* LÓGICA DE COMPRA */ },
                colors = ButtonDefaults.buttonColors(
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