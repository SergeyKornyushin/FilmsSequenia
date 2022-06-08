package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Entity
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb.Companion.FILM_ID
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb.Companion.GENRE_NAME
/**
 * Таблица many-to-many relationship
 * между GenreDb & FilmDb
*/
@Entity(primaryKeys = [FILM_ID, GENRE_NAME])
data class FilmsGenresCrossRef(
    val filmId: Int,
    val genreName: String
)