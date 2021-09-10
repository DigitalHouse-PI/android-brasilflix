package com.grupo7.brasilflixapp.model.reviews

import com.google.gson.annotations.SerializedName
import com.grupo7.brasilflixapp.model.films.films

data class Reviews (

    val author_details: List<ReviewsUser>

)