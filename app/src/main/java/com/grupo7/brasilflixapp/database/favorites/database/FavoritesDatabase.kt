package com.grupo7.brasilflixapp.database.favorites.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.database.favorites.dao.FavoritesDao
import com.grupo7.brasilflixapp.database.favorites.model.Favorites

object FavoritesDatabase {

    @Database(entities = [Favorites::class], version = 1)
    abstract class DatabaseFavoritesMovies : RoomDatabase() {
        abstract fun favoritesDao(): FavoritesDao
    }

    fun getDatabase(context: Context) : DatabaseFavoritesMovies {
        return Room.databaseBuilder(
            context,
            DatabaseFavoritesMovies::class.java, "favoritesMovies_db"
        ).build()
    }
}