package com.grupo7.brasilflixapp.model.films

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
) {}