package com.grupo7.brasilflixapp.ui.fragments.popular.repository

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository

class PopularRepository : BaseRepository() {

    suspend fun getPopular(): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getPopular(1)
        }
    }

}