package com.grupo7.brflixapp.ui.model.reviews

import com.google.gson.annotations.SerializedName

data class AuthorResults(
    @SerializedName("author_details")
    val author_details: AuthorDetails,
    )