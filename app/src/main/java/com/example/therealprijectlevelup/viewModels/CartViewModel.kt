package com.example.therealprijectlevelup.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therealprijectlevelup.R // IMPORTANTE
import com.example.therealprijectlevelup.models.CartItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    init {
        loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch {
            delay(1000)

            // ASIGNAMOS LAS MISMAS IMÁGENES AL CARRITO
            _cartItems.value = listOf(
                CartItem(1, "Audifonos gamer estilo creeper", 2, "41.980", R.drawable.img_audifonos),
                CartItem(2, "Mesa gamer RGB", 1, "110.990", R.drawable.img_escritorio)
            )
        }
    }
}