package com.grupo7.brasilflixapp.model.films

import com.google.gson.annotations.SerializedName

data class films(
    @SerializedName("poster_path")
    var poster_path : String,
    @SerializedName("release_date")
    var release_date : String,
    @SerializedName("title")
    var title : String
){}