package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.ui.screens.flightscreen.FlightRow

@Composable
fun FavoriteResult(
    modifier: Modifier = Modifier,
    airportList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        items(
            items = favoriteList,
            key = { it.id }
        ) { favorite ->

            val departureAirport =
                airportList.find {
                    it.code == favorite.departureCode
                }

            val destinationAirport =
                airportList.find {
                    it.code == favorite.destinationCode
                }

            if (
                departureAirport != null &&
                destinationAirport != null
            ) {

                FlightRow(
                    isFavorite = true,

                    departureAirportCode =
                        departureAirport.code,

                    departureAirportName =
                        departureAirport.name,

                    destinationAirportCode =
                        destinationAirport.code,

                    destinationAirportName =
                        destinationAirport.name,

                    onFavoriteClick =
                        onFavoriteClick
                )
            }
        }
    }
}