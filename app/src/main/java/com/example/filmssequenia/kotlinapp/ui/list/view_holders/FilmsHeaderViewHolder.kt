package com.example.filmssequenia.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmssequenia.R
import com.example.filmssequenia.databinding.HeaderItemBinding
import com.example.filmssequenia.kotlinapp.mvp.models.entities.FilmsHeader

/**
 * ViewHolder для заголовка блока с Films
 */
class FilmsHeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {
    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var filmsHeader: FilmsHeader

    fun bind(filmsHeader: FilmsHeader) {
        this.filmsHeader = filmsHeader

        showTitleText()
    }

    private fun showTitleText() {
        binding.headerText.text = filmsHeader.name
    }
}