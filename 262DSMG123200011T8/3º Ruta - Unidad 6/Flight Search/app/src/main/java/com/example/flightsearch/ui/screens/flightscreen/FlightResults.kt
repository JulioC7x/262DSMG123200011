package com.example.flightsearch.ui.screens.flightscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite

@Composable
fun FlightResults(
    modifier: Modifier = Modifier,
    departureAirport: Airport,
    destinationList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (
        String,
        String
    ) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        items(
            items = destinationList,
            key = { it.id }
        ) { destination ->

            val isFavorite =
                favoriteList.any { favorite ->

                    favorite.departureCode ==
                            departureAirport.code &&

                            favorite.destinationCode ==
                            destination.code
                }

            FlightRow(
                isFavorite = isFavorite,

                departureAirportCode =
                    departureAirport.code,

                departureAirportName =
                    departureAirport.name,

                destinationAirportCode =
                    destination.code,

                destinationAirportName =
                    destination.name,

                onFavoriteClick =
                    onFavoriteClick
            )
        }
    }
}