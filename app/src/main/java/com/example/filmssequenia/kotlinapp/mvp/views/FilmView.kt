package com.example.filmssequenia.kotlinapp.mvp.views

import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.views.base.BaseView
import com.example.filmssequenia.kotlinapp.mvp.views.base.ContentLoadingView
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FilmView : BaseView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilm(film: Film, genresWithYear: String)
}