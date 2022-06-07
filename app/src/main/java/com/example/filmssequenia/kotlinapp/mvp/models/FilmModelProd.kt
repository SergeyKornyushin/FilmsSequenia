package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.wrappers.SelectedFilmMapper
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

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