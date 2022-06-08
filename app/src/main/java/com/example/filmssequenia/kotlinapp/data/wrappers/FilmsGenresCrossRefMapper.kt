package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.kotlinapp.data.database.relations.FilmsGenresCrossRef
import com.example.filmssequenia.kotlinapp.data.entities.network.FilmsDto
import com.example.filmssequenia.kotlinapp.mvp.models.entities.ResponseMapper

/**
 * Маппер сетевого entity к entity базы даннах для
 * FilmDb/GenreDb many-to-many relationship
 */
class FilmsGenresCrossRefMapper :
    ResponseMapper<FilmsDto, List<FilmsGenresCrossRef>> {
    override fun map(films: FilmsDto): List<FilmsGenresCrossRef> {
        val listFilmsGenres: MutableList<FilmsGenresCrossRef> = mutableListOf()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                if (genre.isNotBlank()) {
                    listFilmsGenres.add(
                        FilmsGenresCrossRef(
                            filmId = film.id ?: 0,
                            genreName = genre.replaceFirstChar { it.uppercase() }
                        )
                    )
                }
            }
        }
        return listFilmsGenres
    }
}