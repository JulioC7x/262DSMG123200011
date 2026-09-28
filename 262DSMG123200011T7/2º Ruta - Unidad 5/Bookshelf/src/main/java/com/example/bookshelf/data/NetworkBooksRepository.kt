package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(): List<BookItem> {
        return booksApiService.searchBooks("jazz")
            .docs.orEmpty()
            .filter { it.coverId != null && !it.title.isNullOrBlank() }
            .take(30)
            .map { doc ->
                BookItem(
                    id = doc.key.orEmpty(),
                    volumeInfo = VolumeInfo(
                        title = doc.title.orEmpty(),
                        imageLinks = ImageLinks(
                            thumbnail = "https://covers.openlibrary.org/b/id/${doc.coverId}-M.jpg"
                        )
                    )
                )
            }
    }
}