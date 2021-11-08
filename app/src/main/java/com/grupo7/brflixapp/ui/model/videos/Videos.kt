package com.grupo7.brflixapp.ui.model.videos

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("name")
    var name: String?,
    @SerializedName("key")
    var key: String?
)
