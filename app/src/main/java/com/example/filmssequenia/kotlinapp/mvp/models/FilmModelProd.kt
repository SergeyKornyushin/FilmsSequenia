package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.wrappers.SelectedFilmMapper
import kotlinx.coroutines.*

/**
 * Реализация модели FilmModel
 */
class FilmModelProd(
    private val selectedFilmMapper: SelectedFilmMapper,
    coroutineDispatcher: CoroutineDispatcher
) : FilmModel {

    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    /**
     * Возвращает view entity Film
     */
    override fun getSelectedFilm(filmId: Int, callback: FilmModel.GetFilmCallback) {
        scope.launch {
            callback.onSuccess(
                selectedFilmMapper.getFilmById(filmId)
            )
        }
    }
}