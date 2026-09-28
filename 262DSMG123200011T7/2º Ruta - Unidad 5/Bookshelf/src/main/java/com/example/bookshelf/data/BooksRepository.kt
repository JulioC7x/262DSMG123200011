package com.example.bookshelf.data

import com.example.bookshelf.model.BookItem

interface BooksRepository {
    suspend fun getBooks(): List<BookItem>
}