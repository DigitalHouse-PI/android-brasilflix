package com.grupo7.brasilflixapp.endpoint

import com.grupo7.brasilflixapp.model.films.filmsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointUpComing {

    @GET("movie/upcoming")
    fun getUpComing(@Query("page") page: Int ) : Call<filmsResults>
}