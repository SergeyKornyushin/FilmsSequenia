package com.example.filmssequenia.kotlinapp.mvp.models

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.entities.network.FilmsDto
import com.example.filmssequenia.kotlinapp.data.network.ApiService
import com.example.filmssequenia.kotlinapp.data.network.NetworkCallback
import com.example.filmssequenia.kotlinapp.data.wrappers.DatabaseSaver
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.generators.RecyclerViewListFiller
import kotlinx.coroutines.*

/**
 * Реализация модели FilmsModel
 */
class FilmsModelProd(
    private val apiFilms: ApiService,
    private val filmsDao: FilmsDao,
    private val mappersForSave: DatabaseSaver,
    private val mapperRecyclerViewFiller: RecyclerViewListFiller,
    coroutineDispatcher: CoroutineDispatcher
) : FilmsModel {

    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    /**
     * Возвращает List<ListItem>
     * со всеми фильмами для RecyclerView
     */
    override fun getFilms(callback: FilmsModel.GetFilmsCallback) {
        apiFilms.getFilms().enqueue(object : NetworkCallback<FilmsDto> {
            override fun onSuccess(response: FilmsDto?) {
                if (response != null) {
                    scope.launch {
                        filmsDao.clearAllTables()
                        mappersForSave.saveFilms(response, filmsDao)
                        callback.onSuccess(
                            mapperRecyclerViewFiller.createListForRecyclerView(
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
                mapperRecyclerViewFiller.createListForRecyclerView(
                    genres = filmsDao.getAllGenres(),
                    films = filmsDao.getGenreWithFilms(genre.genreName).filmsDb,
                    genre = genre
                )
            )
        }
    }
}