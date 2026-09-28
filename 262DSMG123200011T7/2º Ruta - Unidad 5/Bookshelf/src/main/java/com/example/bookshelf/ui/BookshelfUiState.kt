package com.example.bookshelf.ui

import com.example.bookshelf.model.BookItem

sealed interface BookshelfUiState {
    data object Loading : BookshelfUiState

    data class Success(
        val books: List<BookItem>
    ) : BookshelfUiState

    data object Error : BookshelfUiState
}
