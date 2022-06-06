package com.example.filmssequenia.kotlinapp.mvp.models.base

interface BaseCallback<Type> : ErrorCallback, SuccessCallback<Type> {
    override fun onSuccess(data: Type)

    override fun onError(error: String)
}