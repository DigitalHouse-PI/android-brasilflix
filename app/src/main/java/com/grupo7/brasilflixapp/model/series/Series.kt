package com.grupo7.brasilflixapp.model.series

import com.google.gson.annotations.SerializedName

class Series (
    @SerializedName("poster_path")
    var poster_path : String? = null,
    @SerializedName("first_air_date")
    var first_air_date : String? = null,
    @SerializedName("original_name")
    var original_name : String? = null,
    @SerializedName("vote_average")
    var vote_average: Double? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("overview")
    val overview: String? = null

    )