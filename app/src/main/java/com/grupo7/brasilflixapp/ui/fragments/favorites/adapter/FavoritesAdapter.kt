package com.grupo7.brasilflixapp.ui.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.model.favorites.Favorites
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.search.adapter.searchAdapter

class FavoritesAdapter (
    private val filmsList: List<Favorites>,
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }
    override fun getItemCount() = filmsList.size

    class ViewHolder(
        val binding: FilmsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: Favorites,
        ) = with(binding) {
            films.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = films.title
            }
        }
    }
}