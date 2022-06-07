package com.example.filmssequenia.kotlinapp.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb.Companion.FILM_ID
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb.Companion.GENRE_NAME

data class FilmWithGenres(
    @Embedded val filmDb: FilmDb,
    @Relation(
        parentColumn = FILM_ID,
        entityColumn = GENRE_NAME,
        associateBy = Junction(FilmsGenresCrossRef::class)
    )
    val filmsDb: List<GenreDb>
)