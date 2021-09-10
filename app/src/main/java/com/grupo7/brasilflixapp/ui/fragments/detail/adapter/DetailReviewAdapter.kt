package com.grupo7.brasilflixapp.ui.fragments.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo7.brasilflixapp.R
import com.grupo7.brasilflixapp.databinding.ReviewsBinding
import com.grupo7.brasilflixapp.model.reviews.ReviewsUser

class DetailReviewAdapter (
    private val reviewsList: List<ReviewsUser>,
) : RecyclerView.Adapter<DetailReviewAdapter.ViewHolder>() {

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
            ReviewsUser: ReviewsUser,
        ) = with(binding) {
            ReviewsUser?.let {
                Glide.with(itemView)
                    .load(ReviewsUser.avatar_path)
                    .placeholder(R.drawable.films)
                    .into(reviewImage)
                reviewTitle.text = ("Nota: $ReviewsUser.name")
                reviewComment.text = ReviewsUser.rating.toString()

            }
        }
    }
}