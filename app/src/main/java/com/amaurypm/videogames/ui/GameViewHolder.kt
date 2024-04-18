package com.amaurypm.videogames.ui

import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.videogames.data.remote.model.GameDto
import com.amaurypm.videogames.databinding.ActivityMainBinding
import com.amaurypm.videogames.databinding.GameElementBinding

class GameViewHolder(
    private val binding: GameElementBinding
): RecyclerView.ViewHolder(binding.root) {

    val ivThumbnail = binding.ivThumbnail

    fun bind(game: GameDto){
        binding.tvTitle.text = game.title
    }
}