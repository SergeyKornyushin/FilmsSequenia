package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.kotlinapp.data.entities.database.GenreDb
import com.example.filmssequenia.kotlinapp.data.entities.network.FilmsDto
import com.example.filmssequenia.kotlinapp.mvp.models.entities.ResponseMapper

/**
 * Маппер сетевого entity к entity GenreDb базы даннах
 */
class GenresToDbMapper : ResponseMapper<FilmsDto, List<GenreDb>> {
    override fun map(films: FilmsDto): List<GenreDb> {
        val hashSet = HashSet<String>()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                if (genre.isNotBlank()) hashSet.add(genre)
            }
        }
        val genresList: MutableList<GenreDb> = mutableListOf()
        hashSet.forEach { genre ->
            genresList.add(GenreDb(genre.replaceFirstChar { it.uppercase() }))
        }
        return genresList
    }
}