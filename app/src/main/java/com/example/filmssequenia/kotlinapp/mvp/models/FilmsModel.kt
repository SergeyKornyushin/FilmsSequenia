package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.mvp.models.base.BaseCallback
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.ui.list.ListItem

/**
 * Model для работы с FilmsPage view
 */
interface FilmsModel {

    fun getFilms(callback: GetFilmsCallback)
    fun getFilmsByGenre(genre: Genre, callback: GetFilmsCallback)

    /**
     * Callback для FilmsModel
     */
    interface GetFilmsCallback : BaseCallback<List<ListItem>>
}