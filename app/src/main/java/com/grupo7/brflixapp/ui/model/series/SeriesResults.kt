package com.grupo7.brflixapp.ui.model.series

import com.google.gson.annotations.SerializedName

class SeriesResults (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Series>
    )