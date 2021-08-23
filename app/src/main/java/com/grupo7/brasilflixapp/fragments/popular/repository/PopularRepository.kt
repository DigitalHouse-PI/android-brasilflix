package com.grupo7.brasilflixapp.fragments.popular.repository

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.util.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository

class PopularRepository : BaseRepository() {

    suspend fun getPopular(): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getPopular(1)
        }
    }

}