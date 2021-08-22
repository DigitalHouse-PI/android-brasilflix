package com.grupo7.brasilflixapp.adapter.series

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.adapter.upcoming.upcomingAdapter
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.series.Series


class seriesAdapter (
    private val seriesList: List<Series>,
) : RecyclerView.Adapter<seriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seriesList[position])
    }
    override fun getItemCount() = seriesList.size

    class ViewHolder(
        val binding: FilmsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            series: Series,
        ) = with(binding) {
            series?.let {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/${series.poster_path}")
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = series.original_name
                dataLancamento.text = "Data de lançamento: ${series.first_air_date}"
                voteModelText.text = series.vote_average.toString()
            }
        }
    }
}