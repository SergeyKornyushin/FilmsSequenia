package com.example.filmssequenia.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FilmPageFragmentBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.views.FilmView
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.filmssequenia.utils.image_loader.ImageLoader
import com.example.filmssequenia.utils.image_loader.ImageLoaderListener

/**
 * Fragment с отображением детальной информации о фильме
 */
class FilmPageFragment : BaseWithAppBarNavigationFragment(R.layout.film_page_fragment), FilmView {
    private lateinit var binding: FilmPageFragmentBinding

    private val args: FilmPageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FilmPageFragmentBinding.bind(view)

        initAppBarProvider()

        showFilm(args.film, args.genresWithYear)
    }

    override fun showFilm(film: Film, genresWithYear: String) {
        binding.apply {
            if (film.image_url.isNotEmpty())
                ImageLoader
                    .load(film.image_url)
                    .into(
                        filmPosterImage,
                        object : ImageLoaderListener {
                            override fun onError(error: String) {
                                posterNotFoundImage.isVisible = true
                            }

                            override fun onSuccess() {
                                posterNotFoundImage.isVisible = false
                            }
                        }
                    )
            else posterNotFoundImage.isVisible = true

            filmNameText.text = film.localized_name
            genresYearText.text = genresWithYear
            filmRatingText.text = film.rating
            ratingSourceText.text = getString(R.string.rating_source)
            filmDescriptionText.text = film.description
        }
    }

    private fun initAppBarProvider() {
        appBarProvider?.setAppBarSettings(this)
        (appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = args.film.localized_name
        appBarProvider?.setBackButtonVisibility(true)
        appBarProvider?.setHomeAsUpIndicator(R.drawable.ic_back_button)
    }
}