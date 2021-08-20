package com.grupo7.brasilflixapp.api.popular

import com.grupo7.brasilflixapp.model.films.filmsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointPopular {
    @GET("movie/popular")
    fun getPopular(@Query("page") page: Int ) : Call<filmsResults>

}