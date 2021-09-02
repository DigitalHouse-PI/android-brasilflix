package com.grupo7.brasilflixapp.ui.fragments.detail.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.fragments.detail.repository.DetailRepository
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.series.Series

class DetailUseCase {

    private val detailRepository = DetailRepository()

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return when(val responseApi = detailRepository.getMovieById(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? films
                films?.backdrop_path = films?.backdrop_path?.getFullImageUrl()
                return ResponseApi.Success(films)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun getSeriesById(serieId: Int): ResponseApi {
        return when(val responseApi = detailRepository.getSeriesById(serieId)) {
            is ResponseApi.Success -> {
                val Series = responseApi.data as? Series
                Series?.backdrop_path = Series?.backdrop_path?.getFullImageUrl()
                return ResponseApi.Success(Series)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

}