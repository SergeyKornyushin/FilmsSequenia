package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.wrappers.SelectedFilmMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Реализация модели FilmModel
 */
class FilmModelProd(
    private val selectedFilmMapper: SelectedFilmMapper
) : FilmModel {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun getSelectedFilm(callback: FilmModel.GetFilmCallback, filmId: Int) {
        scope.launch {
            callback.onSuccess(
                selectedFilmMapper.mapFilm(filmId)
            )
        }
    }
}