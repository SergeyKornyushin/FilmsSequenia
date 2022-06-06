package com.example.filmssequenia.kotlinapp.mvp.presenters

import com.example.filmssequenia.kotlinapp.mvp.models.FilmModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModelProd
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.presenters.base.BasePresenter
import com.example.filmssequenia.kotlinapp.mvp.views.FilmsView
import com.example.filmssequenia.kotlinapp.ui.list.ListItem

class FilmsPresenter(private val filmModel: FilmModel) : BasePresenter<FilmsView>() {

    init {
        getFilms()
    }

    private fun getFilms() {
        viewState.startContentLoading()
        (filmModel as FilmModelProd).getFilms(object : FilmModel.GetFilmsCallback {
            override fun onSuccess(data: List<ListItem>) {
                viewState.endContentLoading()
                viewState.showFilms(data)
            }

            override fun onError(error: String) {
                viewState.showContentLoadingError(error)
            }
        })
    }

    fun showFilmsByGenre(genre: Genre) {
        viewState.startContentLoading()
        (filmModel as FilmModelProd).getFilmsByGenre(
            genre = genre,
            callback = object : FilmModel.GetFilmsCallback {
                override fun onSuccess(data: List<ListItem>) {
                    viewState.endContentLoading()
                    viewState.showFilms(data)
                }

                override fun onError(error: String) {
                    viewState.showContentLoadingError(error)
                }
            })
    }
}