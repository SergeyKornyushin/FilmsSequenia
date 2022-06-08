package com.example.filmssequenia.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.HeaderItemBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.GenresHeader

/**
 *  ViewHolder для заголовка блока с Genres
 */
class GenresHeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {
    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var genresHeader: GenresHeader

    fun bind(genresHeader: GenresHeader) {
        this.genresHeader = genresHeader

        showTitleText()
    }

    private fun showTitleText() {
        binding.headerText.text = genresHeader.name
    }
}