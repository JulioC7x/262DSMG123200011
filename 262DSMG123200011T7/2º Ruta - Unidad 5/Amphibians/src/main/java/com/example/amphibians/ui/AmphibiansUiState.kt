package com.example.amphibians.ui

import com.example.amphibians.model.Amphibian

sealed interface AmphibiansUiState {
    data object Loading : AmphibiansUiState

    data class Success(
        val amphibians: List<Amphibian>
    ) : AmphibiansUiState

    data object Error : AmphibiansUiState
}