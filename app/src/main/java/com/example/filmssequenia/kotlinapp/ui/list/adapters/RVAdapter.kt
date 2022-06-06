package com.example.filmssequenia.kotlinapp.ui.list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmssequenia.kotlinapp.application.App
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.models.entities.FilmsHeader
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.models.entities.GenresHeader
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.*

class RVAdapter(layoutInflater: LayoutInflater) :
    BaseSequenceAdapter<ListItem, BaseViewHolder>(layoutInflater) {

    private lateinit var filmViewHolderListener: FilmViewHolder.FilmViewHolderListener
    private lateinit var genreViewHolderListener: GenreViewHolder.GenreViewHolderListener

    override fun getItemViewType(item: ListItem): Int {
        return when (item.data) {
            is GenresHeader -> TYPE_GENRES_HEADER
            is Genre -> TYPE_GENRE
            is FilmsHeader -> TYPE_FILMS_HEADER
            is Film -> TYPE_FILM
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_GENRES_HEADER -> {
                Log.i("test4", "onBindViewHolder: ${holder.itemViewType}")
                (holder as GenresHeaderViewHolder).bind(items[position].data as GenresHeader)
            }
            TYPE_GENRE ->
                (holder as GenreViewHolder).bind(
                    items[position].data as Genre, genreViewHolderListener
                )
            TYPE_FILMS_HEADER -> (holder as FilmsHeaderViewHolder).bind(items[position].data as FilmsHeader)
            TYPE_FILM -> (holder as FilmViewHolder).bind(
                items[position].data as Film,
                filmViewHolderListener
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_GENRES_HEADER -> GenresHeaderViewHolder(layoutInflater, parent)
            TYPE_GENRE -> GenreViewHolder(layoutInflater, parent)
            TYPE_FILMS_HEADER -> FilmsHeaderViewHolder(layoutInflater, parent)
            TYPE_FILM -> FilmViewHolder(layoutInflater, parent)
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override val typesSequence: IntArray
        get() = intArrayOf(
            TYPE_GENRES_HEADER,
            TYPE_GENRE,
            TYPE_FILMS_HEADER,
            TYPE_FILM
        )

    fun updateFilms(items: List<ListItem>) {
        items.forEachIndexed {index,item->
            updateItems(listOf(item), getItemViewType(item,index))
        }
    }

    private fun getItemViewType(item: ListItem, index: Int): Int {
        return getItemViewType(item)
    }

    private companion object {
        const val TYPE_GENRES_HEADER = 0
        const val TYPE_GENRE = 1
        const val TYPE_FILMS_HEADER = 2
        const val TYPE_FILM = 3
    }
}