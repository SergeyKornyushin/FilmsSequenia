package com.example.filmssequenia.kotlinapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmssequenia.kotlinapp.data.database.relations.FilmsGenresCrossRef
import com.example.filmssequenia.kotlinapp.data.entities.database.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb

/**
 * Сущность базы данных RoomDatabase
 */
@Database(
    entities = [
        FilmDb::class,
        GenreDb::class,
        FilmsGenresCrossRef::class
    ],
    version = 1
)
abstract class FilmsDataBase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao
}