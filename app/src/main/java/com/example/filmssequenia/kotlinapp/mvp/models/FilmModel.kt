package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.mvp.models.base.BaseCallback
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film

/**
 * Model для работы с FilmPage view
 */
interface FilmModel {
    fun getSelectedFilm(callback: GetFilmCallback, filmId: Int)

    /**
     * Callback для FilmModel
     */
    interface GetFilmCallback : BaseCallback<Film>
}