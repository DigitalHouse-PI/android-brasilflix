package com.grupo7.brasilflixapp.api.main

import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.series.SeriesResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/top_rated")
    fun getFilmes(@Query("page") page: Int ) : Call<filmsResults>

    @GET("movie/popular")
    fun getPopular(@Query("page") page: Int ) : Call<filmsResults>

    @GET("tv/on_the_air")
    fun getSeries(@Query("page") page: Int ) : Call<SeriesResults>

    @GET("movie/upcoming")
    fun getUpComing(@Query("page") page: Int ) : Call<filmsResults>


}