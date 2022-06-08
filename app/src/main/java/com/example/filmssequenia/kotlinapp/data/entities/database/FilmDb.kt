package com.example.filmssequenia.kotlinapp.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность Film для RoomDatabase
 */
@Entity
data class FilmDb(
    @PrimaryKey(autoGenerate = false)
    val filmId: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val year: String,
    val rating: String,
    val description: String
) {
    companion object {
        const val FILM_ID = "filmId"
    }
}