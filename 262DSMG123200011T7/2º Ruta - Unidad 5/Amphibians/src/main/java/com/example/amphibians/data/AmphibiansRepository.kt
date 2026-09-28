package com.example.amphibians.data

import com.example.amphibians.model.Amphibian

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}