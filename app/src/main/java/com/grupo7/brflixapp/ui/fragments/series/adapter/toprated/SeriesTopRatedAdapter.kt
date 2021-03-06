package com.grupo7.brflixapp.ui.fragments.series.adapter.toprated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.grupo7.brflixapp.R
import com.grupo7.brflixapp.databinding.FilmsBinding
import com.grupo7.brflixapp.ui.model.series.Series

class SeriesTopRatedAdapter(
    private val onClickListener: (series: Series) -> Unit
) : ListAdapter<Series, SeriesTopRatedAdapter.ViewHolder>(Series.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickListener) }
    }

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
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.films)
                    .into(fotoFilme)
                filmeName.text = series.original_name
                dataLancamento.text = series.first_air_date
                voteModelText.text = series.vote_average.toString()
                filmesContainer.setOnClickListener{
                    onClickListener(series)
                }
            }
        }
    }
}