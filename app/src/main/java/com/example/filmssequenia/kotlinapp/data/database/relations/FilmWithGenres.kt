package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.filmssequenia.kotlinapp.data.constants.DataBaseConstants.FILM_ID
import com.example.filmssequenia.kotlinapp.data.constants.DataBaseConstants.GENRE_NAME
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb

/**
 * Отношение Film - Genres
 */
data class FilmWithGenres(
    @Embedded val filmDb: FilmDb,
    @Relation(
        parentColumn = FILM_ID,
        entityColumn = GENRE_NAME,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmsDb: List<GenreDb>
)