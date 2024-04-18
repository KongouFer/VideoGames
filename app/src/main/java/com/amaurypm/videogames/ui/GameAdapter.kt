package com.amaurypm.videogames.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.videogames.data.remote.model.GameDto
import com.amaurypm.videogames.databinding.GameElementBinding
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class GameAdapter(
    private val games: List<GameDto>,
    private val onGameClicked: (GameDto) -> Unit
): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = games[position]

        holder.bind(game)

        //Usando glide
        Glide.with(holder.itemView.context)
            .load(game.image)
            .into(holder.ivThumbnail)

        //Picasso
        /*Picasso.get()
            .load(game.image)
            .into(holder.ivThumbnail)*/


        holder.itemView.setOnClickListener {
            onGameClicked(game)
        }

    }


}