package com.grupo7.brflixapp.ui.fragments.favorites.usecase

import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesDetails
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brflixapp.ui.fragments.favorites.repository.FavoritesRepository

class FavoritesUseCase() {
    private val favoritesRepository = FavoritesRepository()

    suspend fun getFavoritesMovieFromDb() =
        favoritesRepository.getFavoritesMovieFromDb()

    suspend fun removeFavoritesMovieDb(favorites: Favorites) {
        favoritesRepository.removeFavoritesMovieDb(favorites)

    }

    suspend fun getFavoritesSeriesFromDb() =
        favoritesRepository.getFavoritesSeriesFromDb()

    suspend fun removeFavoritesSeriesDb(favorites: FavoritesSeries) {
        favoritesRepository.removeFavoritesSeriesDb(favorites)

    }

    suspend fun removeFavoritesDetailsDb(favorites: FavoritesDetails) {
        favoritesRepository.removeFavoritesDetailsDb(favorites)

    }

}