package com.example.filmssequenia.kotlinapp.di

import com.example.filmssequenia.kotlinapp.data.wrappers.FilmsGenresCrossRefMapper
import com.example.filmssequenia.kotlinapp.data.wrappers.FilmsToDbMapper
import com.example.filmssequenia.kotlinapp.data.wrappers.GenresToDbMapper
import com.example.filmssequenia.kotlinapp.data.wrappers.MappersSet
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModel
import com.example.filmssequenia.kotlinapp.mvp.models.FilmModelProd
import com.example.filmssequenia.kotlinapp.ui.list.generators.DomainListFiller
import com.example.filmssequenia.kotlinapp.ui.list.generators.DomainRecyclerViewMapper
import org.koin.dsl.module

val modelsModule = module {
    factory<FilmModel> {
        FilmModelProd(
            apiFilms = get(),
            mappersForSave = get(),
            filmsDao = get(),
            mapperRvFiller = get()
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
}
