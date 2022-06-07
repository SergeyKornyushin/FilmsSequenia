package com.example.filmssequenia.kotlinapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FragmentFilmPageBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.views.FilmView
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.filmssequenia.utils.image_loader.ImageLoader
import com.example.filmssequenia.utils.image_loader.ImageLoaderListener
import com.sequenia.app_bar_provider.AppBarProvider

class FilmPageFragment : BaseWithAppBarNavigationFragment(R.layout.fragment_film_page), FilmView {
    private lateinit var binding: FragmentFilmPageBinding

    private val args: FilmPageFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appBarProvider = if (context is AppBarProvider) context
        else throw RuntimeException(getString(R.string.app_bar_provider_error))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmPageBinding.bind(view)

        appBarProvider?.setAppBarSettings(this)
        (appBarProvider!!.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = args.film.localized_name
        appBarProvider?.setBackButtonVisibility(true)
        appBarProvider?.setHomeAsUpIndicator(R.drawable.ic_back_button)

        showFilm(args.film, args.genresWithYear)
    }

    override fun showFilm(film: Film, genresWithYear: String) {
        binding.apply {
            if (film.image_url.isNotEmpty())
                ImageLoader
                    .load(film.image_url)
                    .into(imgFilmPoster, object : ImageLoaderListener {
                        override fun onError(error: String) {
                            imgFilmPosterNotFound.isVisible = true
                        }

                        override fun onSuccess() {
                            imgFilmPosterNotFound.isVisible = false
                        }
                    })
            else imgFilmPosterNotFound.isVisible = true

            tvFilmTitle.text = film.localized_name
            tvGenreYear.text = genresWithYear
            tvFilmRating.text = film.rating
            tvRatingSource.text = getString(R.string.rating_source)
            tvFilmDescription.text = film.description
        }
    }
}