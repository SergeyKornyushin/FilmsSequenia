package com.example.filmssequenia.utils.image_loader

import android.content.Context

/**
 * Создание экземпляра загрузчика
 */
interface LoaderCreator {
    fun getInstance(context: Context): ImageLoader
}