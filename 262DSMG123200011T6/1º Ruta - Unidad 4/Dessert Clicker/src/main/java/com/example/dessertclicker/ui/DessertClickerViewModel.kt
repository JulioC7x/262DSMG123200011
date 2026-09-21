package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

class DessertClickerViewModel : ViewModel() {
    var uiState by mutableStateOf(DessertUiState())
        private set

    fun onDessertClicked() {
        val newDessertsSold = uiState.dessertsSold + 1

        val dessertToShow = determineDessertToShow(
            Datasource.dessertList,
            newDessertsSold
        )

        uiState = uiState.copy(
            revenue = uiState.revenue + uiState.currentDessertPrice,
            dessertsSold = newDessertsSold,
            currentDessertImageId = dessertToShow.imageId,
            currentDessertPrice = dessertToShow.price
        )
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {

        var dessertToShow = desserts.first()

        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }

        return dessertToShow
    }

}