package com.grupo7.brasilflixapp.api.main

import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.series.SeriesResults
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/top_rated")
    suspend fun getFilmes(@Query("page") page: Int ) : Response<filmsResults>

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int ) : Response<filmsResults>

    @GET("tv/on_the_air")
    suspend fun getSeries(@Query("page") page: Int ) : Response<SeriesResults>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("page") page: Int ) : Response<filmsResults>

    @GET("search/movie")
    suspend fun searchMovies(@Query("page") page: Int, @Query("query") search: String) : Response<filmsResults>




}