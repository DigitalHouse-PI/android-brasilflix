package com.grupo7.brasilflixapp.data.database.favorites.dao

import androidx.room.*
import com.grupo7.brasilflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brasilflixapp.data.database.favorites.entity.FavoritesDetails

@Dao
interface FavoritesDetailsDao {

    @Query("SELECT * FROM FavoritesDetails")
    suspend fun getAllFavoritesDetails(): List<FavoritesDetails>

    @Query("SELECT * FROM FavoritesDetails WHERE id = :Id")
    suspend fun loadFavoritesDetailsById(Id: Int): FavoritesDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoritesDetails(favoritesList: List<FavoritesDetails>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritesDetails(favorite: FavoritesDetails)

    @Delete
    suspend fun delete(favorites: FavoritesDetails)
}