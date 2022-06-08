package com.example.filmssequenia.kotlinapp.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность Genre для RoomDatabase
 */
@Entity
data class GenreDb(
    @PrimaryKey(autoGenerate = false)
    val genreName: String
) {
    companion object {
        const val GENRE_NAME = "genreName"
    }
}