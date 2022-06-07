package com.example.filmssequenia.kotlinapp.ui.list.adapters.base

/**
 * Обновление списка с помощью DiffUtils
 */
interface DiffUtilsUpdater<T> {

    /**
     * Обновить список с помощью DiffUtils
     */
    fun updateWithDiffUtils(films: List<T>)
}