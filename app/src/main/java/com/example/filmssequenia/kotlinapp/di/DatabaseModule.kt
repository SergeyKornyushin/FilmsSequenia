package com.example.filmssequenia.kotlinapp.di

import androidx.room.Room
import com.example.filmssequenia.kotlinapp.application.App.Companion.context
import com.example.filmssequenia.kotlinapp.data.database.FilmsDao
import com.example.filmssequenia.kotlinapp.data.database.FilmsDataBase
import org.koin.dsl.module

val databaseModule = module {
    single<FilmsDataBase> {
        Room.databaseBuilder(
            context,
            FilmsDataBase::class.java,
            FilmsDataBase.DATABASE_NAME
        ).build()
    }

    single<FilmsDao> {
        val database = get<FilmsDataBase>()
        database.filmsDao()
    }
}
