package com.grupo7.brasilflixapp.model.series

import com.google.gson.annotations.SerializedName

class Series (
    @SerializedName("poster_path")
    var poster_path : String,
    @SerializedName("first_air_date")
    var first_air_date : String,
    @SerializedName("original_name")
    var original_name : String,
    @SerializedName("vote_average")
    var vote_average: Double

    )