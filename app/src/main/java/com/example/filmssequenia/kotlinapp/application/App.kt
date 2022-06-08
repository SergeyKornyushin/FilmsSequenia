package com.example.filmssequenia.kotlinapp.application

import android.app.Application
import com.example.filmssequenia.kotlinapp.di.databaseModule
import com.example.filmssequenia.kotlinapp.di.modelsModule
import com.example.filmssequenia.kotlinapp.di.networkModule
import com.example.filmssequenia.kotlinapp.di.presentersModule
import com.example.filmssequenia.utils.image_loader.ImageLoader
import com.example.filmssequenia.utils.image_loader.PicassoLoaderCreator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Класс Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        ImageLoader.loaderCreator = PicassoLoaderCreator()

        startKoin {
            androidContext(this@App)
            modules(listOf(databaseModule, networkModule, modelsModule, presentersModule))
        }
    }

    companion object {
        lateinit var context: App
            private set
    }
}