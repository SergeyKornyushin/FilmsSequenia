package com.example.filmssequenia.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FragmentFilmPageBinding
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseFragment

class FilmPageFragment : BaseFragment(R.layout.fragment_film_page) {
    private lateinit var binding: FragmentFilmPageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmPageBinding.bind(view)
    }
}