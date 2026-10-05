package com.example.flightsearch.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.FlightRepository
import com.example.flightsearch.data.UserPreferencesRepository
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(SearchUiState())

    val uiState =
        _uiState.asStateFlow()

    private var searchJob: Job? = null

    private var destinationsJob: Job? = null

    init {
        observePreferences()
        observeFavorites()
    }

    private fun observePreferences() {

        viewModelScope.launch {

            userPreferencesRepository
                .userPreferencesFlow
                .collectLatest { preferences ->

                    updateSearch(
                        preferences.searchValue
                    )
                }
        }
    }

    private fun observeFavorites() {

        viewModelScope.launch {

            flightRepository
                .getAllFavorites()
                .collectLatest { favorites ->

                    _uiState.update {
                        it.copy(
                            favoriteList = favorites
                        )
                    }

                    loadFavoriteAirports(favorites)
                }
        }
    }

    private suspend fun loadFavoriteAirports(
        favorites: List<com.example.flightsearch.model.Favorite>
    ) {

        val codes =
            favorites
                .flatMap {
                    listOf(
                        it.departureCode,
                        it.destinationCode
                    )
                }
                .distinct()

        if (codes.isEmpty()) {

            _uiState.update {
                it.copy(
                    favoriteAirports = emptyList()
                )
            }

            return
        }

        val airports =
            codes.mapNotNull { code ->

                try {
                    flightRepository.getAirportByCode(code)
                } catch (e: Exception) {
                    null
                }
            }

        _uiState.update {
            it.copy(
                favoriteAirports = airports
            )
        }
    }

    fun onQueryChange(query: String) {

        _uiState.update {
            it.copy(
                searchQuery = query,
                selectedAirportCode = null
            )
        }

        destinationsJob?.cancel()

        viewModelScope.launch {

            userPreferencesRepository
                .updateSearchValue(query)
        }

        updateSearch(query)
    }

    private fun updateSearch(query: String) {

        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }

        searchJob?.cancel()

        if (query.isBlank()) {

            _uiState.update {
                it.copy(
                    airportList = emptyList(),
                    selectedAirportCode = null,
                    destinationList = emptyList()
                )
            }

            return
        }

        searchJob =
            viewModelScope.launch {

                flightRepository
                    .searchAirports(query.trim())
                    .collectLatest { airports ->

                        _uiState.update {
                            it.copy(
                                airportList = airports
                            )
                        }
                    }
            }
    }

    fun selectAirport(code: String) {

        val airport =
            _uiState.value.airportList.find {
                it.code == code
            }

        _uiState.update {
            it.copy(
                selectedAirportCode = code,
                selectedAirportName = airport?.name ?: "",
                searchQuery = code,
                destinationList = emptyList()
            )
        }

        destinationsJob?.cancel()

        destinationsJob =
            viewModelScope.launch {

                flightRepository
                    .getDestinations(code)
                    .collectLatest { destinations ->

                        _uiState.update {
                            it.copy(
                                destinationList = destinations
                            )
                        }
                    }
            }
    }

    fun onFavoriteClick(
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
                        departureCode = departureCode,
                        destinationCode = destinationCode
                    )
                )

            } else {

                flightRepository.deleteFavorite(
                    favorite
                )
            }
        }
    }

    fun toggleFavorite(departureCode: String, destinationCode: String) {}

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

                    SearchViewModel(
                        flightRepository =
                            application
                                .container
                                .flightRepository,

                        userPreferencesRepository =
                            application
                                .userPreferencesRepository
                    )
                }
            }
    }

}