package com.example.flightsearch.ui.screens.flightscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.NavigationDestination
import com.example.flightsearch.R

object FlightScreenDestination : NavigationDestination {

    override val route =
        "flight_screen"

    override val titleRes =
        R.string.app_name

    const val codeArg = "code"

    val routeWithArgs =
        "$route/{$codeArg}"
}

@Composable
fun FlightScreen() {

    val viewModel: FlightViewModel =
        viewModel(
            factory = FlightViewModel.Factory
        )

    val uiState =
        viewModel.uiState.collectAsState().value

    Column {

        FlightResults(

            departureAirport =
                uiState.departureAirport,

            destinationList =
                uiState.destinationList,

            favoriteList =
                uiState.favoriteList,

            onFavoriteClick = {
                    departureCode,
                    destinationCode ->

                viewModel.toggleFavorite(
                    departureCode,
                    destinationCode
                )
            }
        )
    }
}