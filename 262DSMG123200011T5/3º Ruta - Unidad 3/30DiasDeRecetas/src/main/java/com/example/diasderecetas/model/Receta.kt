package com.example.diasderecetas.model

import androidx.annotation.DrawableRes


data class Receta(
    val dia: Int,
    val titulo: String,
    @DrawableRes val imagenResId: Int,
    val descripcion: String
)