package com.lucasesteves.brasilflixapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.databinding.FilmesBinding
import com.lucasesteves.brasilflixapp.model.Filmes


class FilmesAdapter (
    private val filmesList: List<Filmes>,
) : RecyclerView.Adapter<FilmesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmesList[position])
    }
    override fun getItemCount() = filmesList.size

    class ViewHolder(
        val binding: FilmesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            Filmes: Filmes,
        ) = with(binding) {
            Filmes?.let {
                    Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/w500/${Filmes.poster_path}")
                        .placeholder(R.drawable.films)
                        .into(fotoFilme)
                    filmeName.text = Filmes.title
                    dataLancamento.text = "Data de lançamento: ${Filmes.release_date}"
                }
        }
    }
}
