package com.example.filmssequenia.kotlinapp.mvp.presenters

import com.example.filmssequenia.kotlinapp.mvp.models.FilmModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModelProd
import com.example.filmssequenia.kotlinapp.mvp.models.FilmsModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmsModelProd
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.presenters.base.BasePresenter
import com.example.filmssequenia.kotlinapp.mvp.views.FilmsView
import com.example.filmssequenia.kotlinapp.ui.list.ListItem

/**
 * Презентер для работы с FilmsPage view
 */
class FilmsPresenter(private val filmsModel: FilmsModel, private val filmModel: FilmModel) :
    BasePresenter<FilmsView>() {

    init {
        getFilms()
    }

    fun getFilms() {
        viewState.startContentLoading()
        (filmsModel as FilmsModelProd).getFilms(object : FilmsModel.GetFilmsCallback {
            override fun onSuccess(data: List<ListItem>) {
                viewState.endContentLoading()
                viewState.showFilms(data)
            }

            override fun onError(error: String) {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error)
            }
        })
    }

    fun showFilmsByGenre(genre: Genre) {
        (filmsModel as FilmsModelProd).getFilmsByGenre(
            genre = genre,
            callback = object : FilmsModel.GetFilmsCallback {
                override fun onSuccess(data: List<ListItem>) {
                    viewState.showFilms(data)
                }

                override fun onError(error: String) {
                    viewState.showContentLoadingError(error)
                }
            }
        )
    }

    fun getFilm(filmId: Int) {
        (filmModel as FilmModelProd).getSelectedFilm(
            filmId = filmId,
            callback = object : FilmModel.GetFilmCallback {
                override fun onSuccess(data: Film) {
                    val stringBuilder = StringBuilder()
                    data.genres.forEach { genre ->
                        stringBuilder.append("${genre.lowercase()}, ")
                    }
                    stringBuilder.append(data.year)

                    viewState.showFilm(data, stringBuilder.toString())
                }

                override fun onError(error: String) {
                    viewState.showContentLoadingError(error)
                }
            }
        )
    }
}