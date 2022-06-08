package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.entities.network.FilmsDto
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

/**
 * Реализация модели FilmsModel
 */
class FilmsModelProd(
    private val apiFilms: ApiService,
    private val filmsDao: FilmsDao,
    private val mappersForSave: MappersSet,
    private val mapperRvFiller: DomainListFiller
) : FilmsModel {

    // todo create dispatchers DI
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /**
     * Возвращает List<ListItem>
     * со всеми фильмами для RecyclerView
     */
    override fun getFilms(callback: FilmsModel.GetFilmsCallback) {
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

    /**
     * Возвращает List<ListItem>
     * с фильмами конкретного жанра для RecyclerView
     */
    override fun getFilmsByGenre(genre: Genre, callback: FilmsModel.GetFilmsCallback) {
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