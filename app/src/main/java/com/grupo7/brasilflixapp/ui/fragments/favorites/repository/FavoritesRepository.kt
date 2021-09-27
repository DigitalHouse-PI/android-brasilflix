package com.grupo7.brasilflixapp.ui.fragments.favorites.repository

import android.app.Application
import com.grupo7.brasilflixapp.base.BaseRepository
import com.grupo7.brasilflixapp.database.allmovies.database.AllMoviesDatabase
import com.grupo7.brasilflixapp.database.favorites.database.FavoritesDatabase
import com.grupo7.brasilflixapp.database.favorites.model.Favorites

class FavoritesRepository (
    private val application: Application
): BaseRepository() {

    suspend fun getFavoritesMovieFromDb() =
        FavoritesDatabase.getDatabase(application)
            .favoritesDao().getAllFavorites()

    suspend fun removeFavoritesMovieDb(favorites: Favorites) = FavoritesDatabase.getDatabase(application)
    .favoritesDao().delete(favorites)


}
