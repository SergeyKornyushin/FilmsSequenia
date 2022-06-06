package com.example.filmssequenia.kotlinapp.data.entities

import com.google.gson.annotations.SerializedName

data class FilmsDto(
    @SerializedName("films")
    val filmsDto: MutableList<FilmDto>
)