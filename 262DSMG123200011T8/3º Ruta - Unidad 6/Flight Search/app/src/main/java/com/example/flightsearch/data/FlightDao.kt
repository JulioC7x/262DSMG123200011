package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    /*
     * Busca aeropuertos para el autocompletado.
     *
     * Se compara tanto el código IATA como el nombre.
     * Los resultados se ordenan por cantidad de pasajeros,
     * de mayor a menor.
     */
    @Query(
        """
        SELECT * FROM airport
        WHERE iata_code LIKE '%' || :query || '%'
           OR name LIKE '%' || :query || '%'
        ORDER BY passengers DESC
        """
    )
    fun searchAirports(query: String): Flow<List<Airport>>

    /*
     * Obtiene los destinos posibles desde un aeropuerto.
     *
     * Cada aeropuerto puede viajar a todos los demás,
     * excepto a sí mismo.
     */
    @Query(
        """
        SELECT * FROM airport
        WHERE iata_code != :departureCode
        ORDER BY passengers DESC
        """
    )
    fun getDestinations(departureCode: String): Flow<List<Airport>>

    /*
     * Obtiene un aeropuerto por su código IATA.
     */
    @Query(
        """
        SELECT * FROM airport
        WHERE iata_code = :code
        LIMIT 1
        """
    )
    suspend fun getAirportByCode(code: String): Airport

    /*
     * Obtiene todos los favoritos.
     *
     * Se utiliza Flow para que Compose reciba automáticamente
     * los cambios de Room.
     */
    @Query(
        """
        SELECT * FROM favorite
        ORDER BY id ASC
        """
    )
    fun getAllFavorites(): Flow<List<Favorite>>

    /*
     * Busca un favorito concreto.
     *
     * Puede no existir, por eso devuelve Favorite?.
     */
    @Query(
        """
        SELECT * FROM favorite
        WHERE departure_code = :departureCode
          AND destination_code = :destinationCode
        LIMIT 1
        """
    )
    suspend fun getFavorite(
        departureCode: String,
        destinationCode: String
    ): Favorite?

    /*
     * Guarda un favorito.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    /*
     * Elimina un favorito.
     */
    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM airport WHERE iata_code IN (:codes)")
    suspend fun getAirportsByCodes(
        codes: List<String>
    ): List<Airport>
}