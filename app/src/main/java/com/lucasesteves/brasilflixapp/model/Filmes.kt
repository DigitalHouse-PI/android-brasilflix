package com.lucasesteves.brasilflixapp.model

import com.google.gson.annotations.SerializedName

data class Filmes(
    @SerializedName("poster_path")
    var poster_path : String,
    @SerializedName("release_date")
    var release_date : String,
    @SerializedName("title")
    var title : String
){}