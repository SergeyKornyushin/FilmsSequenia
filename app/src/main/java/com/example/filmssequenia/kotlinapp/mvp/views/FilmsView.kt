package com.example.filmssequenia.kotlinapp.mvp.views

import com.example.filmssequenia.kotlinapp.mvp.models.entities.Film
import com.example.filmssequenia.kotlinapp.mvp.views.base.ContentLoadingView
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FilmsView : ContentLoadingView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilms(films: List<ListItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showFilm(film: Film)
}