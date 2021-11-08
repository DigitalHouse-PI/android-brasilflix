package com.grupo7.brflixapp.ui.fragments.detail.seriedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brflixapp.R
import com.grupo7.brflixapp.databinding.ReviewsBinding
import com.grupo7.brflixapp.ui.model.reviews.AuthorResults

class DetailReviewSeriesAdapter (
    private val reviewsList: List<AuthorResults>,
) : RecyclerView.Adapter<DetailReviewSeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReviewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewsList[position])
    }

    override fun getItemCount() = reviewsList.size

    class ViewHolder(
        val binding: ReviewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            Result: AuthorResults,
        ) = with(binding) {
            Result?.let {
                Glide.with(itemView)
                    .load(Result.author_details.avatar_path)
                    .placeholder(R.drawable.iconbrflixnovo)
                    .into(reviewImage)
                reviewTitle.text = Result.author_details.name
                reviewRating.text = Result.author_details.rating.toString()

            }
        }
    }
}