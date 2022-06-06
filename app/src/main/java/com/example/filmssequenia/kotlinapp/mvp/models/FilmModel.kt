package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.mvp.models.base.BaseCallback
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.ui.list.ListItem

interface FilmModel {

    fun getFilms(callback: GetFilmsCallback)
    fun getFilmsByGenre(callback: GetFilmsCallback, genre: Genre)

    interface GetFilmsCallback : BaseCallback<List<ListItem>>
}