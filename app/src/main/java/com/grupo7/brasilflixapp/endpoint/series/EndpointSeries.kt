package com.grupo7.brasilflixapp.endpoint.series

import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.series.SeriesResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointSeries {

    @GET("tv/popular")
    fun getSeries(@Query("page") page: Int ) : Call<SeriesResults>

}