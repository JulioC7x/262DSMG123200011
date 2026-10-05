package com.example.flightsearch.ui.screens.flightscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.FlightRepository
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(FlightUiState())

    val uiState =
        _uiState.asStateFlow()

    private val airportCode: String =
        savedStateHandle[
            FlightScreenDestination.codeArg
        ] ?: ""

    init {

        loadDepartureAirport()

        observeDestinations()

        observeFavorites()
    }

    private fun loadDepartureAirport() {

        viewModelScope.launch {

            val airport =
                flightRepository.getAirportByCode(
                    airportCode
                )

            _uiState.update {
                it.copy(
                    departureAirport = airport
                )
            }
        }
    }

    /*
     * Room obtiene directamente los destinos
     * excluyendo el aeropuerto de salida.
     */
    private fun observeDestinations() {

        viewModelScope.launch {

            flightRepository
                .getDestinations(airportCode)
                .collectLatest { destinations ->

                    _uiState.update {
                        it.copy(
                            destinationList =
                                destinations
                        )
                    }
                }
        }
    }

    /*
     * Los favoritos se observan mediante Flow.
     */
    private fun observeFavorites() {

        viewModelScope.launch {

            flightRepository
                .getAllFavorites()
                .collectLatest { favorites ->

                    _uiState.update {
                        it.copy(
                            favoriteList =
                                favorites
                        )
                    }
                }
        }
    }

    fun toggleFavorite(
        departureCode: String,
        destinationCode: String
    ) {

        viewModelScope.launch {

            val favorite =
                flightRepository.getFavorite(
                    departureCode,
                    destinationCode
                )

            if (favorite == null) {

                flightRepository.insertFavorite(
                    Favorite(
                        departureCode =
                            departureCode,

                        destinationCode =
                            destinationCode
                    )
                )

            } else {

                flightRepository.deleteFavorite(
                    favorite
                )
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {

                initializer {

                    val application =
                        this[
                            ViewModelProvider
                                .AndroidViewModelFactory
                                .APPLICATION_KEY
                        ] as FlightApplication

                    FlightViewModel(
                        savedStateHandle =
                            this.createSavedStateHandle(),

                        flightRepository =
                            application
                                .container
                                .flightRepository
                    )
                }
            }
    }
}

