package com.lucasesteves.brasilflixapp.endpoint

import com.lucasesteves.brasilflixapp.model.films.filmsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/top_rated")
    fun getFilmes(@Query("page") page: Int ) : Call<filmsResults>
}