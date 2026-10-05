package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(
    private val flightDao: FlightDao
) : FlightRepository {
    override fun searchAirports(
        query: String
    ): Flow<List<Airport>> {
        return flightDao.searchAirports(query)
    }

    override fun getDestinations(
        departureCode: String
    ): Flow<List<Airport>> {
        return flightDao.getDestinations(departureCode)
    }

    override suspend fun getAirportByCode(
        code: String
    ): Airport {
        return flightDao.getAirportByCode(code)
    }

    override fun getAllFavorites(): Flow<List<Favorite>> {
        return flightDao.getAllFavorites()
    }

    override suspend fun getFavorite(
        departureCode: String,
        destinationCode: String
    ): Favorite? {
        return flightDao.getFavorite(
            departureCode,
            destinationCode
        )
    }

    override suspend fun insertFavorite(
        favorite: Favorite
    ) {
        flightDao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(
        favorite: Favorite
    ) {
        flightDao.deleteFavorite(favorite)
    }

    override suspend fun getAirportsByCodes(
        codes: List<String>
    ): List<Airport> {
        return flightDao.getAirportsByCodes(codes)
    }
}