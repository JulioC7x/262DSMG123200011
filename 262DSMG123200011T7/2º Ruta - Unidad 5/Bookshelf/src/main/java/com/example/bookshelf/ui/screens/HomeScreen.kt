package com.example.bookshelf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.ui.BookshelfUiState

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFD2A679))
    ) {

        // Título
        Text(
            text = "BOOKSHELF",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        // Libros debajo
        when (bookshelfUiState) {

            is BookshelfUiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.weight(1f)
                )
            }

            is BookshelfUiState.Success -> {
                BooksGrid(
                    books = bookshelfUiState.books,
                    modifier = Modifier.weight(1f)
                )
            }

            is BookshelfUiState.Error -> {
                ErrorScreen(
                    retryAction = retryAction,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BooksGrid(
    books: List<BookItem>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(books) { book ->

            BookCard(book)
        }
    }
}

@Composable
fun BookCard(
    book: BookItem
) {
    androidx.compose.foundation.layout.Column {

        AsyncImage(
            model = book.volumeInfo.imageLinks?.thumbnail
                ?.replace("http://", "https://"),
            contentDescription = book.volumeInfo.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = book.volumeInfo.title,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        androidx.compose.foundation.layout.Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "No se pudieron cargar los libros."
            )

            androidx.compose.material3.Button(
                onClick = retryAction
            ) {
                Text("Reintentar")
            }
        }
    }
}