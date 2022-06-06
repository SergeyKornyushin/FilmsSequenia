package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.entities.FilmsDto
import com.example.filmssequenia.kotlinapp.data.network.ApiService
import com.example.filmssequenia.kotlinapp.data.network.NetworkCallback
import com.example.filmssequenia.kotlinapp.data.wrappers.MappersSet
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.generators.DomainListFiller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FilmModelProd(
    private val apiFilms: ApiService,
    private val filmsDao: FilmsDao,
    private val mappersForSave: MappersSet,
    private val mapperRvFiller: DomainListFiller
) : FilmModel {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun getFilms(callback: FilmModel.GetFilmsCallback) {
        apiFilms.getFilms().enqueue(object : NetworkCallback<FilmsDto> {
            override fun onSuccess(response: FilmsDto?) {
                if (response != null) {
                    scope.launch {
                        mappersForSave.saveFilms(response, filmsDao)
                        callback.onSuccess(
                            mapperRvFiller.createListForRecyclerView(
                                genres = filmsDao.getAllGenres(),
                                films = filmsDao.getAllFilms(),
                                null
                            )
                        )
                    }
                } else emptyList<ListItem>()
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    override fun getFilmsByGenre(callback: FilmModel.GetFilmsCallback, genre: Genre) {
        scope.launch {
            callback.onSuccess(
                mapperRvFiller.createListForRecyclerView(
                    genres = filmsDao.getAllGenres(),
                    films = filmsDao.getGenreWithFilms(genre.genreName).filmsDb,
                    genre = genre
                )
            )
        }
    }
}