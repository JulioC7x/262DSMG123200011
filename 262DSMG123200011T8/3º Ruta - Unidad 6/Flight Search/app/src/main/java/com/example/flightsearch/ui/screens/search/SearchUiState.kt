package com.example.flightsearch.ui.screens.search

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite

data class SearchUiState(
    val searchQuery: String = "",

    val airportList: List<Airport> = emptyList(),

    val destinationList: List<Airport> = emptyList(),

    val favoriteList: List<Favorite> = emptyList(),

    val favoriteAirports: List<Airport> = emptyList(),

    val selectedAirportCode: String? = null,

    val selectedAirportName: String = ""
)