package com.example.filmssequenia.kotlinapp.di

import com.example.filmssequenia.kotlinapp.mvp.presenters.FilmsPresenter
import org.koin.dsl.module

val presentersModule = module {
    factory {
        FilmsPresenter(
            filmModel = get()
        )
    }
}