package com.example.filmssequenia.kotlinapp.di

import com.example.filmssequenia.kotlinapp.data.wrappers.*
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModelProd
import com.example.filmssequenia.kotlinapp.mvp.models.FilmsModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmsModelProd
import com.example.filmssequenia.kotlinapp.ui.list.generators.DomainListFiller
import com.example.filmssequenia.kotlinapp.ui.list.generators.DomainRecyclerViewMapper
import org.koin.dsl.module

val modelsModule = module {
    factory<FilmsModel> {
        FilmsModelProd(
            apiFilms = get(),
            mappersForSave = get(),
            filmsDao = get(),
            mapperRvFiller = get()
        )
    }

    factory<FilmModel> {
        FilmModelProd(
            selectedFilmMapper = get()
        )
    }

    factory<MappersSet> {
        MappersSet.Base(
            FilmsToDbMapper(),
            GenresToDbMapper(),
            FilmsGenresCrossRefMapper()
        )
    }

    factory<DomainListFiller> {
        DomainListFiller.Base(domainRVMapper = get())
    }

    factory<DomainRecyclerViewMapper> {
        DomainRecyclerViewMapper.Base()
    }

    factory<SelectedFilmMapper> {
        SelectedFilmMapper.Base(filmsDao = get())
    }
}
