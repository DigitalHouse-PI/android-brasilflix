package com.grupo7.brflixapp.ui.fragments.detail.moviedetail.usecase

import com.grupo7.brflixapp.data.api.util.ResponseApi
import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesDetails
import com.grupo7.brflixapp.extensions.getDateBR
import com.grupo7.brflixapp.extensions.getFullImageUrl
import com.grupo7.brflixapp.ui.fragments.detail.moviedetail.repository.DetailRepository
import com.grupo7.brflixapp.ui.model.films.films
import com.grupo7.brflixapp.ui.model.reviews.ReviewResults
import com.grupo7.brflixapp.ui.model.videos.VideosResults

class DetailUseCase() {

    private val detailRepository = DetailRepository()

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return when (val responseApi = detailRepository.getMovieById(movieId)) {
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


    suspend fun getReviewsMovies(movieId: Int): ResponseApi {
        return when (val responseApi = detailRepository.getReviewsMovies(movieId)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? ReviewResults
                val result = data?.results?.map {
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



    suspend fun saveFavoritesDb(favorites: Favorites) =
        detailRepository.saveFavoritesDb(favorites)

    suspend fun saveFavoritesDetailsDb(favorites: FavoritesDetails) =
        detailRepository.saveFavoritesDetailsDb(favorites)

    suspend fun getFavoritesDetailsDb(favoritesId: Int) =
        detailRepository.getFavoritesDetailsDb(favoritesId)

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return when(val responseApi = detailRepository.getMoviesVideos(movieId)) {
            is ResponseApi.Success -> {
                val films = responseApi.data as? VideosResults
                val result = films?.results
                return ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }

        }
    }



}