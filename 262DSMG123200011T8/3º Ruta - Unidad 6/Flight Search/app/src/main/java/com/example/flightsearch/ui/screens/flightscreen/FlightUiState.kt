package com.example.flightsearch.ui.screens.flightscreen

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite

data class FlightUiState(
    val departureAirport: Airport = Airport(),
    val destinationList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList()
)
