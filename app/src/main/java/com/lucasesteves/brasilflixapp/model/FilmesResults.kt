package com.lucasesteves.brasilflixapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FilmesResults(
    val page: Int,
    val results: List<Filmes>
)