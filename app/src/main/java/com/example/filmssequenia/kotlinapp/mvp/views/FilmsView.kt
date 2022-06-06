package com.example.filmssequenia.kotlinapp.mvp.views

import com.example.filmssequenia.kotlinapp.mvp.views.base.ContentLoadingView
import com.example.filmssequenia.kotlinapp.ui.list.ListItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FilmsView : ContentLoadingView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilms(films: List<ListItem>)
}