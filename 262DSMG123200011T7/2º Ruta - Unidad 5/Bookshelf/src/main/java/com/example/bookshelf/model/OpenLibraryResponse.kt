package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

data class OpenLibraryResponse(
    val docs: List<OpenLibraryDoc>? = null
)

data class OpenLibraryDoc(
    val key: String? = null,
    val title: String? = null,
    @SerializedName("cover_i") val coverId: Int? = null
)