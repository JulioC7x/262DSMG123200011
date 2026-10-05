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

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    onSelectCode: (String) -> Unit
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

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {

                AirportRow(
                    code = airport.code,

                    name = airport.name,

                    onSelectCode =
                        onSelectCode,

                    modifier =
                        Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }
}