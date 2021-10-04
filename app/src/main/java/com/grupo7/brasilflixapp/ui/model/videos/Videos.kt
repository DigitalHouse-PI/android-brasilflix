package com.grupo7.brasilflixapp.ui.model.videos

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("name")
    var name: String?,
    @SerializedName("release_date")
    var key: String?
)
