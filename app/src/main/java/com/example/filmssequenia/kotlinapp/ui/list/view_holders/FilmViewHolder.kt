package com.example.filmssequenia.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FilmItemBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.utils.image_loader.ImageLoader

open class FilmViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.film_item) {
    private var binding: FilmItemBinding = FilmItemBinding.bind(itemView)
    private lateinit var film: Film
    private lateinit var listener: FilmViewHolderListener

    fun bind(film: Film, listener: FilmViewHolderListener) {
        this.film = film
        this.listener = listener

        showFilmInfo()
        setListener()
    }

    private fun showFilmInfo() {
        binding.tvFilmName.text = film.localized_name
        ImageLoader
            .load(film.image_url)
            .error(android.R.drawable.stat_notify_error)
            .centerCrop()
            .roundedCorners(4)
            .into(binding.imgPoster)
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onFilmClick(film)
        }
    }

    interface FilmViewHolderListener {
        fun onFilmClick(film: Film)
    }
}