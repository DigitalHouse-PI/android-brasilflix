package com.grupo7.brflixapp.ui.fragments.detail.moviedetail.repository

import android.app.Application
import com.grupo7.brflixapp.data.api.main.RetrofitInstance
import com.grupo7.brflixapp.data.api.util.ResponseApi
import com.grupo7.brflixapp.base.BaseRepository
import com.grupo7.brflixapp.data.database.movies.allmovies.database.AllMoviesDatabase
import com.grupo7.brflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesDetails
import com.grupo7.brflixapp.util.constants.Constants.Home.FIRST_PAGE
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailRepository(
) : BaseRepository(), KoinComponent {

    private val application: Application by inject()

    suspend fun getMovieById(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiMovies.getMovieById(movieId)
        }
    }

    suspend fun getReviewsMovies(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiMovies.getReviewsMovies(movieId, FIRST_PAGE)
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        AllMoviesDatabase.getDatabase(application)
            .allmoviesDao().loadAllMoviesById(movieId)

    suspend fun getFavoritesDetailsDb(favoriteId: Int) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesDetailsDao().loadFavoritesDetailsById(favoriteId)

    suspend fun saveFavoritesDetailsDb(favorites: FavoritesDetails) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesDetailsDao().insertFavoritesDetails(favorites)

    suspend fun saveFavoritesDb(favorites: Favorites) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesDao().insertFavorites(favorites)

    suspend fun getMoviesVideos(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApiMovies.getMoviesVideos(movieId)
        }
    }




}