package com.example.dessertclicker.ui

import com.example.dessertclicker.R

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertImageId: Int = R.drawable.cupcake,
    val currentDessertPrice: Int = 5
)
