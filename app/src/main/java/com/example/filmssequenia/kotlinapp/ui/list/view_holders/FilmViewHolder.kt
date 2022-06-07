package com.example.filmssequenia.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FilmItemBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.utils.ResourcesUtils
import com.example.filmssequenia.utils.image_loader.ImageLoader
import com.example.filmssequenia.utils.image_loader.ImageLoaderListener

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
        if (film.image_url.isNotEmpty())
            ImageLoader
                .load(film.image_url)
                .into(binding.imgPoster, object : ImageLoaderListener{
                    override fun onError(error: String) {
                        binding.imgPosterNotFound.isVisible = true
                    }

                    override fun onSuccess() {
                        binding.imgPosterNotFound.isVisible = false
                    }

                })
        else {
            binding.imgPosterNotFound.isVisible = true
            binding.imgPoster.setImageDrawable(null)
        }
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onFilmClick(film.filmId)
        }
    }

    interface FilmViewHolderListener {
        fun onFilmClick(filmId: Int)
    }
}