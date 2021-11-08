package com.grupo7.brflixapp.ui.fragments.search.repository

import com.grupo7.brflixapp.data.api.main.RetrofitInstance
import com.grupo7.brflixapp.data.api.util.ResponseApi
import com.grupo7.brflixapp.base.BaseRepository

class SearchRepository: BaseRepository() {

    suspend fun searchMovies(search: String): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSearch.searchMovies(search)
        }
    }

    suspend fun searchSeries(search: String): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiSearch.searchSeries(search)
        }
    }
}