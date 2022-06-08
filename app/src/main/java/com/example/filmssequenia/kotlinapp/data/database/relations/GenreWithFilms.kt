package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.filmssequenia.kotlinapp.data.constants.DataBaseConstants.FILM_ID
import com.example.filmssequenia.kotlinapp.data.constants.DataBaseConstants.GENRE_NAME
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb

/**
 * Отношение Genre - Films
 */
data class GenreWithFilms(
    @Embedded val genreDb: GenreDb,
    @Relation(
        parentColumn = GENRE_NAME,
        entityColumn = FILM_ID,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmsDb: List<FilmDb>
)