package com.example.filmssequenia.kotlinapp.mvp.views.base

import android.view.View
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType
//
///**
// * Интерфейс для управления состоянием загрузки контента на view
// */
//interface ContentLoading : ContentLoadingView {
//
//    @StateStrategyType(AddToEndSingleTagStrategy::class)
//    fun getContentView(): View? = null
//
//    @StateStrategyType(AddToEndSingleTagStrategy::class)
//    fun getContentLoadingView(): View? = null
//
//    override fun startContentLoading() {
//        setContentViewVisibility(false)
//        setContentLoadingViewVisibility(true)
//    }
//
//    override fun endContentLoading() {
//        setContentViewVisibility(true)
//        setContentLoadingViewVisibility(false)
//    }
//
//    override fun showContentLoadingError(error: String) {
//        endContentLoading()
//    }
//
//    private fun setContentViewVisibility(isVisible: Boolean) {
//        getContentView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
//    }
//
//    private fun setContentLoadingViewVisibility(isVisible: Boolean) {
//        getContentLoadingView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
//    }
//}