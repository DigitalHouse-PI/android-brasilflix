package com.grupo7.brasilflixapp.ui.fragments.detail.repository

import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository

class DetailRepository: BaseRepository() {

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getMovieById(movieId)
        }
    }

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesById(serieId)
        }
    }

}