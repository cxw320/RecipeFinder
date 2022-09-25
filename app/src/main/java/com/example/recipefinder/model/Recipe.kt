package com.example.recipefinder.model

data class Recipe(
    val id: Long = -1,
    val title: String = "",
    val imageUrl: String = "",
    val summary: String = ""
)