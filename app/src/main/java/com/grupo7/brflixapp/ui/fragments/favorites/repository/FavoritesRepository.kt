package com.grupo7.brflixapp.ui.fragments.favorites.repository

import android.app.Application
import com.grupo7.brflixapp.base.BaseRepository
import com.grupo7.brflixapp.data.database.favorites.database.FavoritesDatabase
import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesDetails
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesSeries
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoritesRepository (): BaseRepository(), KoinComponent {

    private val application: Application by inject()

    suspend fun getFavoritesMovieFromDb() =
        FavoritesDatabase.getDatabase(application)
            .favoritesDao().getAllFavorites()

    suspend fun removeFavoritesMovieDb(favorites: Favorites) = FavoritesDatabase.getDatabase(application)
    .favoritesDao().delete(favorites)

    suspend fun getFavoritesSeriesFromDb() =
        FavoritesDatabase.getDatabase(application)
            .favoritesSeriesDao().getAllFavoritesSeries()


    suspend fun removeFavoritesSeriesDb(favorites: FavoritesSeries) = FavoritesDatabase.getDatabase(application)
        .favoritesSeriesDao().delete(favorites)

    suspend fun removeFavoritesDetailsDb(favorites: FavoritesDetails) = FavoritesDatabase.getDatabase(application)
        .favoritesDetailsDao().delete(favorites)
}
