package com.example.therealprijectlevelup.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therealprijectlevelup.R // IMPORTANTE: PARA ACCEDER A R.drawable
import com.example.therealprijectlevelup.models.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val products = mutableStateOf<List<Product>>(emptyList())

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            delay(1000)

            // ASIGNAMOS LAS IMÁGENES QUE SUBISTE
            products.value = listOf(
                Product(1, "Silla Gamer", "250.990", R.drawable.img_silla),
                Product(2, "Mesa Gamer", "110.990", R.drawable.img_escritorio),
                Product(3, "Microfono", "80.000", R.drawable.img_microfono),
                Product(4, "Audifonos Gamer", "20.990", R.drawable.img_audifonos),
                Product(5, "Cooler Pad", "15.000", R.drawable.img_cooler),
                Product(6, "Mouse Gamer", "12.000", R.drawable.img_mause)
            )
        }
    }
}