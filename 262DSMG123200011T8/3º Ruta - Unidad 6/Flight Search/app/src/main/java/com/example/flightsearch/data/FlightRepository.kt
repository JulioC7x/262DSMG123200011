package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun searchAirports(query: String): Flow<List<Airport>>

    fun getDestinations(departureCode: String): Flow<List<Airport>>

    suspend fun getAirportByCode(code: String): Airport

    fun getAllFavorites(): Flow<List<Favorite>>

    suspend fun getFavorite(
        departureCode: String,
        destinationCode: String
    ): Favorite?

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)

    suspend fun getAirportsByCodes(
        codes: List<String>
    ): List<Airport>
}