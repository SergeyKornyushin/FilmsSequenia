package com.example.filmssequenia.kotlinapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreDb(
    @PrimaryKey(autoGenerate = false)
    val genreName: String
){
    companion object{
        const val GENRE_NAME = "genreName"
    }
}