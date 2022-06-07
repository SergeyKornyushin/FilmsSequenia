package com.example.filmssequenia.kotlinapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FragmentFilmsListBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.models.entities.Genre
import com.example.filmssequenia.kotlinapp.mvp.presenters.FilmsPresenter
import com.example.filmssequenia.kotlinapp.mvp.views.FilmsView
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseFragment
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseNavigationFragment
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import com.example.filmssequenia.kotlinapp.ui.list.adapters.base.ListExtension
import com.example.filmssequenia.kotlinapp.ui.list.adapters.RVAdapter
import com.example.filmssequenia.kotlinapp.ui.list.adapters.RVFilmsSpanSize
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.filmssequenia.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.filmssequenia.kotlinapp.ui.utils.ScreenLocker
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

class FilmsListFragment : BaseNavigationFragment(R.layout.fragment_films_list), FilmsView,
    ScreenLocker,
    FilmViewHolder.FilmViewHolderListener, GenreViewHolder.GenreViewHolderListener {

    private lateinit var binding: FragmentFilmsListBinding
    private var listExtension: ListExtension? = null
    private lateinit var adapter: RVAdapter

    private val presenter by moxyPresenter {
        get<FilmsPresenter>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmsListBinding.bind(view)
        adapter = RVAdapter(layoutInflater)
        adapter.setAdapters(this, this)

        listExtension = ListExtension(binding.rvFilmsList)
        listExtension!!.setAdapter(adapter)

        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = RVFilmsSpanSize(adapter)
        listExtension!!.setLayoutManager(gridLayoutManager)

        binding.rvFilmsList.itemAnimator = null
        val defaultItemAnimator = DefaultItemAnimator()
        binding.rvFilmsList.itemAnimator = defaultItemAnimator
        val itemAnimator = binding.rvFilmsList.itemAnimator
        if (itemAnimator is DefaultItemAnimator) itemAnimator.supportsChangeAnimations = false

    }

    override fun showFilms(films: List<ListItem>) {
        adapter.updateWithDiffUtils(films)
    }

    override fun showFilm(film: Film) {
        val action = FilmsListFragmentDirections.actionFilmsListToFilmPage(film)
        navigate(action)
    }

    override fun startContentLoading() {
        binding.pbDownload.isVisible = true
        lockScreen()
    }

    override fun endContentLoading() {
        binding.pbDownload.isVisible = false
        unlockScreen()
    }

    override fun showContentLoadingError(error: String) {

    }

    override fun onFilmClick(filmId: Int) {
        presenter.getFilm(filmId)
    }

    override fun onGenreClick(genre: Genre) {
        presenter.showFilmsByGenre(genre)
    }
}