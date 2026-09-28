package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            BookshelfTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: BookshelfViewModel =
                        viewModel(
                            factory = BookshelfViewModel.Factory
                        )

                    HomeScreen(
                        bookshelfUiState =
                            viewModel.bookshelfUiState
                                .collectAsState()
                                .value,

                        retryAction =
                            viewModel::getBooks
                    )
                }
            }
        }
    }
}
