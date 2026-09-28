package com.example.bookshelf.network

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksResponse
import com.example.bookshelf.model.OpenLibraryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 30,
        @Query("fields") fields: String = "key,title,cover_i"
    ): OpenLibraryResponse
}