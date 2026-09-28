package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService

class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<com.example.amphibians.model.Amphibian> =
        amphibiansApiService.getAmphibians()
}