package com.grupo7.brasilflixapp.fragments.home.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.fragments.home.repository.HomeRepository
import com.grupo7.brasilflixapp.model.films.filmsResults

class HomeUseCase {

    private val homeRepository = HomeRepository()

    suspend fun getTopRatedMovies(): ResponseApi {
        return when (val responseApi = homeRepository.getTopRatedMovies()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? filmsResults
                val result = data?.results?.map {
                    it.poster_path = "https://image.tmdb.org/t/p/w500${it.poster_path}"
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun getUpcomingMovies(): ResponseApi {
        return when (val responseApi = homeRepository.getUpcomingMovies()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? filmsResults
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