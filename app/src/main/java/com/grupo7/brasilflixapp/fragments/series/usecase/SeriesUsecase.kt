package com.grupo7.brasilflixapp.fragments.series.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.fragments.series.repository.SeriesRepository
import com.grupo7.brasilflixapp.model.films.filmsResults
import com.grupo7.brasilflixapp.model.series.SeriesResults

class SeriesUsecase {

    private val seriesRepository = SeriesRepository()

    suspend fun getSeries(): ResponseApi {
        return when (val responseApi = seriesRepository.getSeries()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? SeriesResults
                val result = data?.results?.map {
                    it.poster_path = it.poster_path.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }
}