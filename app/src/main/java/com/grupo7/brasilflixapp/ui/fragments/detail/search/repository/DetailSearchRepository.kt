package com.grupo7.brasilflixapp.ui.fragments.detail.moviedetail.repository

import android.app.Application
import com.grupo7.brasilflixapp.data.api.main.RetrofitInstance
import com.grupo7.brasilflixapp.data.api.util.ResponseApi
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.data.database.movies.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.data.database.series.allseries.database.AllSeriesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brasilflixapp.util.constants.Constants.Home.FIRST_PAGE

class DetailSearchRepository(
    private val application: Application
) : BaseRepository() {

    suspend fun getMovieByIdSearch(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getMovieById(movieId)
        }
    }

    suspend fun getSeriesByIdSearch(serieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getSeriesById(serieId)
        }
    }

    suspend fun getReviewsMoviesSearch(movieId: Int): ResponseApi {
        return safeApiCall {
            RetrofitInstance.tmdbApi.getReviewsMovies(movieId, FIRST_PAGE)
        }
    }

    suspend fun getMovieByIdFromDbSearch(movieId: Int) =
        AllMoviesDatabase.getDatabase(application)
            .allmoviesDao().loadAllMoviesById(movieId)

    suspend fun getSerieByIdFromDbSearch(serieId: Int) =
        AllSeriesDatabase.getDatabase(application)
            .allseriesDao().loadAllSeriesById(serieId)

    suspend fun saveFavoritesDbSearch(favorites: Favorites) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesDao().insertFavorites(favorites)

    suspend fun saveFavoritesSeriesDbSearch(favorites: FavoritesSeries) =
        FavoritesDatabase.getDatabase(
            application
        ).favoritesSeriesDao().insertFavoritesSeries(favorites)


}