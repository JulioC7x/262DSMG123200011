package com.example.flightsearch.ui.screens.flightscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.flightsearch.ui.screens.search.AirportRow

@Composable
fun FlightRow(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    departureAirportCode: String,
    departureAirportName: String,
    destinationAirportCode: String,
    destinationAirportName: String,
    onFavoriteClick: (
        String,
        String
    ) -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Row {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {

                Text(
                    text = "DEPART",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 24.dp)
                )

                AirportRow(
                    code = departureAirportCode,
                    name = departureAirportName
                )

                Text(
                    text = "ARRIVE",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 24.dp,top = 4.dp)
                )

                AirportRow(
                    code = destinationAirportCode,
                    name = destinationAirportName
                )
            }

            IconButton(
                onClick = {
                    onFavoriteClick(
                        departureAirportCode,
                        destinationAirportCode
                    )
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {

                Icon(
                    imageVector =
                        if (isFavorite) {
                            Icons.Filled.Star
                        } else {
                            Icons.Outlined.StarBorder
                        },

                    tint =
                        if (isFavorite) {
                            Color(0xFFFFC107)
                        } else {
                            Color.LightGray
                        },

                    contentDescription =
                        if (isFavorite) {
                            "Remove favorite"
                        } else {
                            "Add favorite"
                        }
                )
            }
        }
    }
}