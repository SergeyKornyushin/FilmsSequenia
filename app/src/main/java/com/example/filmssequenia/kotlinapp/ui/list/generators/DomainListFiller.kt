package com.example.filmssequenia.kotlinapp.ui.list.generators

import android.util.Log
import com.example.filmssequenia.R
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb
import com.example.filmssequenia.kotlinapp.mvp.models.entities.FilmsHeader
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.models.entities.GenresHeader
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.ListItemTypes
import com.example.filmssequenia.utils.ResourcesUtils

interface DomainListFiller {
    suspend fun createListForRecyclerView(
        genres: List<GenreDb>,
        films: List<FilmDb>,
        genre: Genre?
    ): List<ListItem>

    class Base(
        private val domainRVMapper: DomainRecyclerViewMapper
    ) : DomainListFiller {

        private val titles: List<ListItem> = listOf(
            ListItem(
                type = ListItemTypes.TEXT,
                id = "1",
                data = GenresHeader(
                    name = ResourcesUtils.getString(R.string.genres_title)
                )
            ),
            ListItem(
                type = ListItemTypes.TEXT,
                id = "2",
                data = FilmsHeader(
                    name = ResourcesUtils.getString(R.string.films_title)
                )
            )
        )

        override suspend fun createListForRecyclerView(
            genres: List<GenreDb>,
            films: List<FilmDb>,
            genre: Genre?
        ): List<ListItem> {
            val rvList: MutableList<ListItem> = mutableListOf()
            rvList.add(titles[GENRES_HEADER])
            if (genre == null){
                rvList.addAll(domainRVMapper.mapGenresToDomain(genres))
            } else {
                rvList.addAll(domainRVMapper.mapGenresWithSelect(genres, genre))
            }
            rvList.add(titles[FILMS_HEADER])
            rvList.addAll(domainRVMapper.mapFilmsToDomain(films))
            return rvList
        }
    }

    private companion object{
        const val GENRES_HEADER = 0
        const val FILMS_HEADER = 1
    }
}