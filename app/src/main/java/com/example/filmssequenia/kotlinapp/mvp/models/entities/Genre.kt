package com.example.filmssequenia.kotlinapp.mvp.models.entities

/**
 * Entity для работы во view
 */
data class Genre(
    val genreName: String,
    var isSelected: Boolean = false
)