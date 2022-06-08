package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film

/**
 * Маппер для преобразования entity базы данных
 * в entity для работы во View
 */
interface SelectedFilmMapper {
    suspend fun mapFilm(filmId: Int): Film

    /**
     * Базовая реализация интерфейса SelectedFilmMapper
     */
    class Base(
        private val filmsDao: FilmsDao,
    ) : SelectedFilmMapper {

        override suspend fun mapFilm(filmId: Int): Film {
            val filmFromDb = filmsDao.getFilmById(filmId)
            val filmGenresFromDb = filmsDao.getFilmWithGenres(filmId)
            val film = Film(
                filmId = filmFromDb.filmId,
                image_url = filmFromDb.image_url,
                localized_name = filmFromDb.localized_name,
                name = filmFromDb.name,
                year = filmFromDb.year,
                rating = filmFromDb.rating,
                description = filmFromDb.description,
                genres = filmGenresFromDb.filmsDb.map { genreDb ->
                    genreDb.genreName
                }
            )
            return film
        }
    }
}
