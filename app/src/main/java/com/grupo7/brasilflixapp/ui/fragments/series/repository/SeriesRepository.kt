package com.grupo7.brasilflixapp.ui.fragments.series.repository

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.base.BaseRepository

class SeriesRepository : BaseRepository() {
    suspend fun getSeries(): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeries(1)
        }
    }
}