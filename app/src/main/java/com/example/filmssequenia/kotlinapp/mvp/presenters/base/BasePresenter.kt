package com.example.filmssequenia.kotlinapp.mvp.presenters.base

import com.example.filmssequenia.kotlinapp.mvp.views.base.BaseView
import moxy.MvpPresenter

open class BasePresenter<View : BaseView> : MvpPresenter<View>()