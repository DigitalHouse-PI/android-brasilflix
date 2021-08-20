package com.grupo7.brasilflixapp.api.main

import com.grupo7.brasilflixapp.model.films.filmsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/top_rated")
    fun getFilmes(@Query("page") page: Int ) : Call<filmsResults>


}