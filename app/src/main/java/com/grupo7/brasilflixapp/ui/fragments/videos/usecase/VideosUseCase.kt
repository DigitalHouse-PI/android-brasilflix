package com.grupo7.brasilflixapp.ui.fragments.videos.usecase

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.extensions.getFullYoutubeUrl
import com.grupo7.brasilflixapp.ui.fragments.detail.main.repository.DetailRepository
import com.grupo7.brasilflixapp.ui.fragments.videos.repository.VideosRepository
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.series.Series
import com.grupo7.brasilflixapp.ui.model.videos.Videos

class VideosUseCase(
    private val application: Application
) {

    private val videosRepository = VideosRepository(application)

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getMoviesVideos(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? Videos
                films?.key = films?.key?.getFullYoutubeUrl()
                return ResponseApi.Success(films)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

    suspend fun getSeriesVideos(serieId: Int): ResponseApi {
        return when(val responseApi = videosRepository.getSeriesVideos(serieId)) {
            is ResponseApi.Success -> {
                val Series = responseApi.data as? Videos
                Series?.key = Series?.key?.getFullYoutubeUrl()
                return ResponseApi.Success(Series)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

}