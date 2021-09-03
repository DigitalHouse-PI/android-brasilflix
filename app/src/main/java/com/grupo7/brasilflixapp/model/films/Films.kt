package com.grupo7.brasilflixapp.model.films

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class films(
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String
) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<films> =
            object : DiffUtil.ItemCallback<films>() {
                override fun areItemsTheSame(oldItem: films, newItem: films): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: films, newItem: films): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }


}