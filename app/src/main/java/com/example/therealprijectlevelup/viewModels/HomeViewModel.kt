package com.example.therealprijectlevelup.viewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.therealprijectlevelup.models.Product

class HomeViewModel : ViewModel() {

    val products = mutableStateOf(
        listOf(
            Product(1, "Silla Gamer", "250.990"),
            Product(2, "Mesa Gamer", "110.990"),
            Product(3, "Microfono", "80.000"),
            Product(4, "Audifonos Gamer", "20.990"),
            Product(5, "Cooler Pad", "15.000"),
            Product(6, "Mouse Gamer", "12.000")
        )
    )
}
