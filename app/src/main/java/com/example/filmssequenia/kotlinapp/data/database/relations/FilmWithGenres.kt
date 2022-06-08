package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb.Companion.FILM_ID
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb.Companion.GENRE_NAME

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