package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb.Companion.FILM_ID
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb.Companion.GENRE_NAME

data class GenreWithFilms(
    @Embedded val genreDb: GenreDb,
    @Relation(
        parentColumn = GENRE_NAME,
        entityColumn = FILM_ID,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmsDb: List<FilmDb>
)