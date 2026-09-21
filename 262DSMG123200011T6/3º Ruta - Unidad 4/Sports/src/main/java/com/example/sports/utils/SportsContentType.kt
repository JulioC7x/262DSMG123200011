package com.example.sports.utils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

fun getSportsContentType(
    widthSizeClass: WindowWidthSizeClass
): SportsContentType {
    return when (widthSizeClass) {
        WindowWidthSizeClass.Expanded -> SportsContentType.ListAndDetail
        else -> SportsContentType.ListOnly
    }
}