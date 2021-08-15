package com.lucasesteves.brasilflixapp.endpoint

import com.lucasesteves.brasilflixapp.model.films.filmsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointUpComing {

    @GET("movie/upcoming")
    fun getUpComing(@Query("page") page: Int ) : Call<filmsResults>
}