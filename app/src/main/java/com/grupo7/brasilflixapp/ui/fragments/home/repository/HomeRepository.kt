package com.grupo7.brasilflixapp.ui.fragments.home.repository

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository

class HomeRepository : BaseRepository() {

    suspend fun getTopRatedMovies(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getFilmes(page)
        }
    }

    suspend fun getUpComingMovies(page: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getUpComing(page)
        }
    }
}