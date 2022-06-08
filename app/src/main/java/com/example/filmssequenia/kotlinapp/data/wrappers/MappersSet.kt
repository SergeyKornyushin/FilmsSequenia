package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.entities.network.FilmsDto

/**
 * Набор мапперов для сохранения сетевого ответа в базу данных
 */
interface MappersSet {
    suspend fun saveFilms(networkFilms: FilmsDto, filmsDao: FilmsDao)

    /**
     * Базовая реализация интерфейса MappersSet
     */
    data class Base(
        private val filmsToDbMapper: FilmsToDbMapper,
        private val genresToDbMapper: GenresToDbMapper,
        private val filmsGenresCrossRefMapper: FilmsGenresCrossRefMapper
    ) : MappersSet {
        override suspend fun saveFilms(
            networkFilms: FilmsDto,
            filmsDao: FilmsDao
        ) {
            filmsDao.clearAllTables()
            filmsToDbMapper.map(networkFilms).forEach { filmsDao.insertFilm(it) }
            genresToDbMapper.map(networkFilms).forEach { filmsDao.insertGenre(it) }
            filmsGenresCrossRefMapper.map(networkFilms)
                .forEach { filmsDao.insertFilmsGenreCrossRef(it) }
        }
    }
}