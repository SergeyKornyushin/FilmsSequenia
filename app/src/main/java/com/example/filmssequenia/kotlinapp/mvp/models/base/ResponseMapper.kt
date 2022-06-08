package com.example.filmssequenia.kotlinapp.mvp.models.base

/**
 * Entity для работы во view
 */
interface ResponseMapper<FILM, T> {
    fun map(films: FILM): T
}