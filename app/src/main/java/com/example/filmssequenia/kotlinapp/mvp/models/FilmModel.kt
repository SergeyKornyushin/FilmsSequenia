package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.mvp.models.base.BaseCallback
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film

interface FilmModel {
    fun getSelectedFilm(callback: GetFilmCallback, filmId: Int)

    interface GetFilmCallback : BaseCallback<Film>
}