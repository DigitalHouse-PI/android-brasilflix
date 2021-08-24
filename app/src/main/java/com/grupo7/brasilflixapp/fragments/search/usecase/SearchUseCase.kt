package com.grupo7.brasilflixapp.fragments.search.usecase

import com.grupo7.brasilflixapp.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.fragments.popular.repository.PopularRepository
import com.grupo7.brasilflixapp.fragments.search.repository.SearchRepository
import com.grupo7.brasilflixapp.model.films.filmsResults

class SearchUseCase {

    private val searchRepository = SearchRepository()

    suspend fun searchMovies(search: String): ResponseApi {
        return when (val responseApi = searchRepository.searchMovies(search)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as filmsResults
                val result = data.results
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }


}