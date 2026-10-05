package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.ui.screens.flightscreen.FlightRow

@Composable
fun FlightResults(
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    favoriteList: List<Favorite>,
    departureCode: String,
    departureAirportName: String,
    onFavoriteClick: (String, String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        items(
            items = airports,
            key = { it.id }
        ) { airport ->

            val isFavorite =
                favoriteList.any {
                    it.departureCode == departureCode &&
                            it.destinationCode == airport.code
                }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {

                FlightRow(
                    isFavorite = isFavorite,

                    departureAirportCode =
                        departureCode,

                    departureAirportName =
                        departureAirportName,

                    destinationAirportCode =
                        airport.code,

                    destinationAirportName =
                        airport.name,

                    onFavoriteClick =
                        onFavoriteClick
                )
            }
        }
    }
}