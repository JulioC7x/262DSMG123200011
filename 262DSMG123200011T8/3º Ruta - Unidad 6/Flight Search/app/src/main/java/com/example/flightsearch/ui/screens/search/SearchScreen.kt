package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.NavigationDestination

object SearchDestination : NavigationDestination {

    override val route = "home"

    override val titleRes =
        R.string.app_name
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSelectCode: (String) -> Unit
) {

    val viewModel: SearchViewModel =
        viewModel(
            factory = SearchViewModel.Factory
        )

    val uiState =
        viewModel.uiState.collectAsState().value

    Column(
        modifier = modifier
    ) {

        Surface(
            color = Color(0xFF1565C0),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Flight Search",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
            )
        }

        SearchTextField(
            query = uiState.searchQuery,

            onQueryChange = {
                viewModel.onQueryChange(it)
            }
        )

        /*
         * Si hay un aeropuerto seleccionado,
         * mostramos los vuelos desde ese aeropuerto.
         */
        if (uiState.selectedAirportCode != null) {

            Text(
                text = "Flights from ${uiState.selectedAirportCode}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
            )

            FlightResults(
                airports = uiState.destinationList,

                favoriteList = uiState.favoriteList,

                departureCode =
                    uiState.selectedAirportCode,

                departureAirportName =
                    uiState.selectedAirportName,

                onFavoriteClick = {
                        departureCode,
                        destinationCode ->

                    viewModel.onFavoriteClick(
                        departureCode,
                        destinationCode
                    )
                }
            )
        } else if (uiState.searchQuery.isBlank()) {

            /*
             * Buscador vacío:
             * mostramos solamente favoritos.
             */
            if (uiState.favoriteList.isNotEmpty()) {

                FavoriteResult(
                    airportList =
                        uiState.favoriteAirports,

                    favoriteList =
                        uiState.favoriteList,

                    onFavoriteClick = {
                            departureCode,
                            destinationCode ->

                        viewModel.onFavoriteClick(
                            departureCode,
                            destinationCode
                        )
                    }
                )

            } else {

                Text(
                    text = "No favorites yet",
                    modifier = Modifier.padding(16.dp)
                )
            }

        } else {

            /*
             * El usuario está buscando un aeropuerto.
             */
            SearchResults(
                airports =
                    uiState.airportList,

                onSelectCode = { code ->

                    viewModel.selectAirport(code)
                }
            )
        }
    }
}