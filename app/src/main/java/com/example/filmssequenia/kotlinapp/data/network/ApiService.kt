package com.example.filmssequenia.kotlinapp.data.network

import com.example.filmssequenia.kotlinapp.data.entities.FilmsDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/sequeniatesttask/films.json")
    fun getFilms(): Call<FilmsDto>
}