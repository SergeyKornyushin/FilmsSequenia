package com.example.filmssequenia.kotlinapp.mvp.models.entities

interface ResponseMapper<FILM, T> {
    fun map(films: FILM): T
}