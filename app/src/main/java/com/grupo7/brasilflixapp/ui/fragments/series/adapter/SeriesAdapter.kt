package com.grupo7.brasilflixapp.ui.fragments.series.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.FilmsBinding
import com.grupo7.brasilflixapp.model.series.Series


class seriesAdapter (
    private val seriesList: List<Series>,
    private val onClickListener: (series: Series) -> Unit
) : RecyclerView.Adapter<seriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seriesList[position], onClickListener)
    }
    override fun getItemCount() = seriesList.size

    class ViewHolder(
        val binding: FilmsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            series: Series,
            onClickListener: (series: Series) -> Unit,
        ) = with(binding) {
            series?.let {
                Glide.with(itemView)
                    .load(series.poster_path)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = series.original_name
                dataLancamento.text = "Data de lançamento: ${series.first_air_date}"
                voteModelText.text = series.vote_average.toString()
                filmesContainer.setOnClickListener{
                    onClickListener(series)
                }
            }
        }
    }
}