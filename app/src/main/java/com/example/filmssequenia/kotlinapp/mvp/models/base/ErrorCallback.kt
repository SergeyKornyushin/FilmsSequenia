package com.example.filmssequenia.kotlinapp.mvp.models.base

interface ErrorCallback {
    /**
     * Срабатывает при ошибке
     *
     * @param error текст ошибки
     */
    fun onError(error: String)
}