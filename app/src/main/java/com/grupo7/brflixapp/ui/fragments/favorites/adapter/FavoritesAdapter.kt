package com.grupo7.brflixapp.ui.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brflixapp.R
import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.databinding.FavoritesBinding

class FavoritesAdapter (
    private val filmsList: List<Favorites>,
    private val onClickListener: (movie: Favorites) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoritesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position], onClickListener)
    }
    override fun getItemCount() = filmsList.size

    class ViewHolder(
        val binding: FavoritesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: Favorites,
            onClickListener: (movie: Favorites) -> Unit,
        ) = with(binding) {
            films.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
//                filmeName.text = films.title
                removeFavorite.setOnClickListener{
                    onClickListener(films)
                }
            }
        }
    }
}