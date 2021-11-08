package com.grupo7.brflixapp.ui.fragments.videos.usecase

import com.grupo7.brflixapp.data.api.util.ResponseApi
import com.grupo7.brflixapp.extensions.getFullYoutubeUrl
import com.grupo7.brflixapp.ui.fragments.videos.repository.VideosRepository
import com.grupo7.brflixapp.ui.model.videos.VideosResults

class VideosUseCase() {

    private val videosRepository = VideosRepository()

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getMoviesVideos(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? VideosResults
                val result = films?.results?.map {
                    it.key = it.key.getFullYoutubeUrl()
                    it
                }
                return ResponseApi.Success(result)

            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getSeriesVideos(serieId)) {
            is ResponseApi.Success -> {
                val Series = responseApi.data as? VideosResults
                val result = Series?.results?.map {
                    it?.key = it?.key?.getFullYoutubeUrl()
                    it
                }
                return ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

}