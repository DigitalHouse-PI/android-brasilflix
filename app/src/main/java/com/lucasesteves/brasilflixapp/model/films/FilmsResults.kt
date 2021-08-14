package com.lucasesteves.brasilflixapp.model.films

import com.lucasesteves.brasilflixapp.model.films.films

data class filmsResults(
    val page: Int,
    val results: List<films>
)