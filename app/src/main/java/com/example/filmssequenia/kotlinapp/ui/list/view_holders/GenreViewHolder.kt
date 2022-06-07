package com.example.filmssequenia.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.GenreItemBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre

class GenreViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.genre_item) {
    private var binding: GenreItemBinding = GenreItemBinding.bind(itemView)
    private lateinit var genre: Genre
    private lateinit var listener: GenreViewHolderListener

    fun bind(genre: Genre, listener: GenreViewHolderListener) {
        this.genre = genre
        this.listener = listener

        showGenreName()
        setListener()
    }

    private fun showGenreName() {
        binding.tvRvTitle.text = genre.genreName
        binding.tvRvTitle.isChecked = genre.isSelected
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick(genre)
        }
    }

    interface GenreViewHolderListener {
        fun onGenreClick(genre: Genre)
    }
}