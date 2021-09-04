package com.grupo7.brasilflixapp.ui.fragments.popular.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.fragments.popular.repository.PopularRepository
import com.grupo7.brasilflixapp.model.films.filmsResults

class PopularUseCase {

    private val popularRepository = PopularRepository()

    suspend fun getPopular(): ResponseApi {
        return when (val responseApi = popularRepository.getPopular()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? filmsResults
                val result = data?.results?.map {
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it.release_date = it.release_date?.getDateBR()
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