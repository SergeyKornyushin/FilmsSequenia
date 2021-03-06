package com.example.filmssequenia.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
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
import com.example.filmssequenia.kotlinapp.ui.list.adapters.base.ListExtension
import com.example.filmssequenia.kotlinapp.ui.list.adapters.base.RVFilmsSpanSize
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.filmssequenia.utils.ScreenLocker
import com.example.filmssequenia.utils.snackbar_holder.MessagesHolder
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FilmsListFragmentBinding.bind(view)

        initRecyclerViewAdapter()
        initAppBarProvider()
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
        ).showUnhiddenNetworkError(error) { presenter.onRepeatButtonClicked() }
    }

    override fun onFilmClick(filmId: Int) {
        presenter.onFilmClicked(filmId)
    }

    override fun onGenreClick(genre: Genre) {
        presenter.onGenreClicked(genre)
    }

    private fun initRecyclerViewAdapter() {
        adapter = RVAdapter(layoutInflater)
        adapter.setListeners(this, this)

        listExtension = ListExtension(binding.filmsList)
        listExtension?.setAdapter(adapter)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = RVFilmsSpanSize(adapter)
        listExtension?.setLayoutManager(gridLayoutManager)

        val itemAnim = binding.filmsList.itemAnimator
        (itemAnim as DefaultItemAnimator).supportsChangeAnimations = false
    }

    private fun initAppBarProvider() {
        appBarProvider?.setAppBarSettings(this)
        appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar)
        (appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = resources.getString(R.string.title_films)
    }
}