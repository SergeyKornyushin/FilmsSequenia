package com.example.filmssequenia.kotlinapp.data.wrappers

import com.example.filmssequenia.kotlinapp.data.entities.FilmsDto
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb
import com.example.filmssequenia.kotlinapp.mvp.models.entities.ResponseMapper

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