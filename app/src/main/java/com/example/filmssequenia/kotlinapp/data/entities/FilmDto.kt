package com.example.filmssequenia.kotlinapp.data.entities

data class FilmDto(
    val id: Int? = 0,
    val genres: MutableList<String>? = mutableListOf(""),
    val image_url: String? = "",
    val localized_name: String? = "",
    val name: String? = "",
    val year: Int? = 0,
    val rating: Double? = -1.0,
    val description: String? = ""
)

