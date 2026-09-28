package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookshelfViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {
    private val _bookshelfUiState =
        MutableStateFlow<BookshelfUiState>(
            BookshelfUiState.Loading
        )

    val bookshelfUiState: StateFlow<BookshelfUiState> =
        _bookshelfUiState.asStateFlow()

    init {
        getBooks()
    }

    fun getBooks() {

        viewModelScope.launch {

            _bookshelfUiState.value =
                BookshelfUiState.Loading

            _bookshelfUiState.value =
                try {
                    BookshelfUiState.Success(
                        booksRepository.getBooks()
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    BookshelfUiState.Error
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
                        ] as BookshelfApplication

                    BookshelfViewModel(
                        booksRepository =
                            application.container.booksRepository
                    )
                }
            }
    }
}