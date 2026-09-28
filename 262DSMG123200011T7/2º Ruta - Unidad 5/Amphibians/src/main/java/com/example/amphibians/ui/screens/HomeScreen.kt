package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.theme.AmphibiansTheme
import com.example.amphibians.R


@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Text(
            text = "Amphibians",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 20.dp,
                bottom = 12.dp
            )
        )

        when (amphibiansUiState) {

            is AmphibiansUiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is AmphibiansUiState.Success -> {
                AmphibiansList(
                    amphibians = amphibiansUiState.amphibians,
                    modifier = Modifier.weight(1f)
                )
            }

            is AmphibiansUiState.Error -> {
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
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Loading..."
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something went wrong",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Could not load the amphibians."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = retryAction
        ) {
            Text("Retry")
        }
    }
}

@Composable
fun AmphibiansList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(amphibians) { amphibian ->
            AmphibianCard(
                amphibian = amphibian
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDCEFD8)
        )
    ) {
        Column {

            Text(
                text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )
            )

            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                )
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img)
            )

            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansListPreview() {
    AmphibiansTheme {
        val mockData = listOf(
            Amphibian(
                name = "Frog",
                type = "Frog",
                description = "A beautiful amphibian.",
                imgSrc = "https://android-kotlin-fun-mars-server.appspot.com/images/frog.jpg"
            ),
            Amphibian(
                name = "Toad",
                type = "Toad",
                description = "Another beautiful amphibian.",
                imgSrc = "https://android-kotlin-fun-mars-server.appspot.com/images/toad.jpg"
            )
        )

        AmphibiansList(
            amphibians = mockData,
            modifier = Modifier.fillMaxSize()
        )
    }
}
