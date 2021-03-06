package com.example.filmssequenia.kotlinapp.mvp.models.base

/**
 * Callback для обработки ошибок
 */
interface ErrorCallback {
    /**
     * Срабатывает при ошибке
     *
     * @param error текст ошибки
     */
    fun onError(error: String)
}