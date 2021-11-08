package com.grupo7.brflixapp.ui.fragments.videos.repository

import com.grupo7.brflixapp.base.BaseRepository
import com.grupo7.brflixapp.data.api.main.RetrofitInstance
import com.grupo7.brflixapp.data.api.util.ResponseApi

class VideosRepository(): BaseRepository()  {

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiMovies.getMoviesVideos(movieId)
        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSeries.getSeriesVideos(serieId)
        }
    }
}