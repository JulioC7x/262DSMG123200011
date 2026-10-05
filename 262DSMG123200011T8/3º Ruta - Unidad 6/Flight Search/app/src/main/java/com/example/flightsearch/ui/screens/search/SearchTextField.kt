package com.example.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = Color(0xFF1565C0),
            backgroundColor = Color(0xFF1565C0).copy(alpha = 0.3f)
        )
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },

            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },

            placeholder = {
                Text(
                    text = "Enter departure airport",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            shape = RoundedCornerShape(24.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(

                focusedContainerColor = Color(0xFFD3E3FD),
                unfocusedContainerColor = Color(0xFFD3E3FD),

                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,

                cursorColor = Color(0xFF1565C0),

                ),
            singleLine = true
        )
    }
}