package com.grupo7.brasilflixapp.ui.fragments.detail.main.usecase

import android.app.Application
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.extensions.getDateBR
import com.grupo7.brasilflixapp.extensions.getFullImageUrl
import com.grupo7.brasilflixapp.ui.fragments.detail.main.repository.DetailRepository
import com.grupo7.brasilflixapp.ui.fragments.detail.main.repository.DetailSearchRepository
import com.grupo7.brasilflixapp.ui.model.films.films
import com.grupo7.brasilflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brasilflixapp.ui.model.series.Series

class DetailUseCase(
    private val application: Application
) {

    private val detailRepository = DetailRepository(application)

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return when(val responseApi = detailRepository.getMovieById(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? films
                films?.backdrop_path = films?.backdrop_path?.getFullImageUrl()
                films?.poster_path = films?.poster_path?.getFullImageUrl()
                films?.release_date = films?.release_date?.getDateBR().toString()
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
                Series?.first_air_date = Series?.first_air_date?.getDateBR().toString()
                return ResponseApi.Success(Series)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }

    suspend fun getReviewsMovies(movieId: Int): ResponseApi {
        return when(val responseApi = detailRepository.getReviewsMovies(movieId)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? ReviewResults
                val result = data?.results?.map{
                    it.author_details.avatar_path = it.author_details.avatar_path.getFullImageUrl()
                    it
                }
                return ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        detailRepository.getMovieByIdFromDb(movieId)

    suspend fun getSerieByIdFromDb(serieId: Int) =
        detailRepository.getSerieByIdFromDb(serieId)

    suspend fun saveFavoritesDb(favorites: Favorites) =
        detailRepository.saveFavoritesDb(favorites)

    suspend fun saveFavoritesSeriesDb(favorites: FavoritesSeries) =
        detailRepository.saveFavoritesSeriesDb(favorites)

}