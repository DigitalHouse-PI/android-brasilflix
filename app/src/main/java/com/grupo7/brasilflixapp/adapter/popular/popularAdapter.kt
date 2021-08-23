package com.grupo7.brasilflixapp.adapter.popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.adapter.upcoming.upcomingAdapter
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.model.films.films

class popularAdapter (
    private val filmsList: List<films>,
) : RecyclerView.Adapter<popularAdapter.ViewHolder>() {

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

        @SuppressLint("SetTextI18n")
        fun bind(
            films: films,
        ) = with(binding) {
            films?.let {
                Glide.with(itemView)
                    .load(films.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = films.title
                dataLancamento.text = "Data de lançamento: ${films.release_date}"
                voteModelText.text = films.vote_average.toString()
            }
        }
    }
}