package com.grupo7.brflixapp.ui.model.videos

import com.google.gson.annotations.SerializedName

data class VideosResults(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<Videos>
)
