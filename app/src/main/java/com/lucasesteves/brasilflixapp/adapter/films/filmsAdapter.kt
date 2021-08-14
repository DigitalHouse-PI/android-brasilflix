package com.lucasesteves.brasilflixapp.adapter.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.databinding.FilmesBinding
import com.lucasesteves.brasilflixapp.model.films.films


class filmsAdapter (
    private val filmsList: List<films>,
) : RecyclerView.Adapter<filmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }
    override fun getItemCount() = filmsList.size

    class ViewHolder(
        val binding: FilmesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            films: films,
        ) = with(binding) {
            films?.let {
                    Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/w500/${films.poster_path}")
                        .placeholder(R.drawable.films)
                        .into(fotoFilme)
                    filmeName.text = films.title
                    dataLancamento.text = "Data de lançamento: ${films.release_date}"
                }
        }
    }
}