package com.example.filmssequenia.kotlinapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FilmsListFragmentBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.presenters.FilmsPresenter
import com.example.filmssequenia.kotlinapp.mvp.views.FilmsView
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.adapters.RVAdapter
import com.example.filmssequenia.kotlinapp.ui.list.adapters.RVFilmsSpanSize
import com.example.filmssequenia.kotlinapp.ui.list.adapters.base.GridSpacingItemDecoration
import com.example.filmssequenia.kotlinapp.ui.list.adapters.base.ListExtension
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.filmssequenia.kotlinapp.ui.utils.MessagesHolder
import com.example.filmssequenia.kotlinapp.ui.utils.ScreenLocker
import com.sequenia.app_bar_provider.AppBarProvider
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

/**
 * Fragment с отображением списка фильмов и жанров
 */
class FilmsListFragment :
    BaseWithAppBarNavigationFragment(R.layout.films_list_fragment),
    FilmsView,
    ScreenLocker,
    FilmViewHolder.FilmViewHolderListener,
    GenreViewHolder.GenreViewHolderListener {

    private lateinit var binding: FilmsListFragmentBinding
    private var listExtension: ListExtension? = null
    private lateinit var adapter: RVAdapter

    private val presenter by moxyPresenter {
        get<FilmsPresenter>()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appBarProvider = if (context is AppBarProvider) context
        else throw RuntimeException("Activity must implement AppBarProvider")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FilmsListFragmentBinding.bind(view)
        adapter = RVAdapter(layoutInflater)
        adapter.setListeners(this, this)

        listExtension = ListExtension(binding.filmsList)
        listExtension?.setAdapter(adapter)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = RVFilmsSpanSize(adapter)
        listExtension?.setLayoutManager(gridLayoutManager)

        binding.filmsList.itemAnimator = null
        binding.filmsList.addItemDecoration(
            GridSpacingItemDecoration(2, 36, true, 15, false)
        )

        appBarProvider?.setAppBarSettings(this)
        appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar)
        (appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = resources.getString(R.string.title_films)
    }

    override fun showFilms(films: List<ListItem>) {
        adapter.updateWithDiffUtils(films)
    }

    override fun showFilm(film: Film, genresWithYear: String) {
        val action = FilmsListFragmentDirections.actionFilmsListToFilmPage(film, genresWithYear)
        navigate(action)
    }

    override fun startContentLoading() {
        binding.downloadProgressBar.isVisible = true
        lockScreen()
    }

    override fun endContentLoading() {
        binding.downloadProgressBar.isVisible = false
        unlockScreen()
    }

    override fun showContentLoadingError(error: String) {
        MessagesHolder(
            viewLifecycleOwner,
            binding.root
        ).showUnhiddenNetworkError(error) { presenter.getFilms() }
    }

    override fun onFilmClick(filmId: Int) {
        presenter.getFilm(filmId)
    }

    override fun onGenreClick(genre: Genre) {
        presenter.showFilmsByGenre(genre)
    }
}