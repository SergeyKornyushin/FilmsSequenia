package com.example.filmssequenia.kotlinapp.ui.list.adapters

import androidx.recyclerview.widget.GridLayoutManager

/**
 * Определяет сколько column будет занимать элемент
 * конкретного типа в RecyclerView
 */
class RVFilmsSpanSize(private val rvAdapter: RVAdapter) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        return when (rvAdapter.getItemViewType(position)) {
            TYPE_GENRES_HEADER -> SINGLE_COLUMN
            TYPE_GENRE -> SINGLE_COLUMN
            TYPE_FILMS_HEADER -> SINGLE_COLUMN
            TYPE_FILM -> DOUBLE_COLUMN
            else -> SINGLE_COLUMN
        }
    }

    private companion object {
        private const val SINGLE_COLUMN = 2
        private const val DOUBLE_COLUMN = 1

        const val TYPE_GENRES_HEADER = 0
        const val TYPE_GENRE = 1
        const val TYPE_FILMS_HEADER = 2
        const val TYPE_FILM = 3
    }
}