package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Entity
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb.Companion.FILM_ID
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb.Companion.GENRE_NAME

@Entity(primaryKeys = [FILM_ID, GENRE_NAME])
data class FilmsGenresCrossRef(
    val filmId: Int,
    val genreName: String
)