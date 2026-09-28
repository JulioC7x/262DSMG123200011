package com.example.amphibians.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository
) : ViewModel() {
    private val _amphibiansUiState =
        MutableStateFlow<AmphibiansUiState>(AmphibiansUiState.Loading)

    val amphibiansUiState: StateFlow<AmphibiansUiState> =
        _amphibiansUiState.asStateFlow()

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {

            _amphibiansUiState.value = AmphibiansUiState.Loading

            _amphibiansUiState.value = try {
                AmphibiansUiState.Success(
                    amphibiansRepository.getAmphibians()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                AmphibiansUiState.Error
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {

                initializer {

                    val application =
                        (this[
                            ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY
                        ] as AmphibiansApplication)

                    AmphibiansViewModel(
                        amphibiansRepository =
                            application.container.amphibiansRepository
                    )
                }
            }
    }
}