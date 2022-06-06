package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.R
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.FilmsDto
import com.example.filmssequenia.kotlinapp.mvp.models.entities.ResponseMapper
import com.example.filmssequenia.utils.ResourcesUtils

class FilmsToDbMapper : ResponseMapper<FilmsDto, List<FilmDb>> {
    override fun map(films: FilmsDto): List<FilmDb> =
        films.filmsDto.map { film ->
            FilmDb(
                filmId = film.id ?: 0,
                image_url = film.image_url ?: "",
                localized_name = film.localized_name.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                name = film.name.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                year = film.year.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                rating = film.rating.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                description = film.description.avoidNullToString(ResourcesUtils.getString(R.string.unknown))
            )
        }
}