package com.example.filmssequenia.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.FragmentFilmPageBinding
import com.example.filmssequenia.kotlinapp.ui.fragments.base.BaseFragment

class FilmPageFragment : BaseFragment(R.layout.fragment_film_page) {
    private lateinit var binding: FragmentFilmPageBinding

    private val args: FilmPageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFilmPageBinding.bind(view)
    }
}