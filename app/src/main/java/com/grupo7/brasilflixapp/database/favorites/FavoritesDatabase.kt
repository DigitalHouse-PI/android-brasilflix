package com.grupo7.brasilflixapp.database.favorites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.brasilflixapp.model.favorites.Favorites

object FavoritesDatabase {

    @Database(entities = [Favorites::class], version = 1)
    abstract class Class1RoomDatabase : RoomDatabase() {
        abstract fun favoritesDao(): FavoritesDao
    }

    fun getDatabase(context: Context) : Class1RoomDatabase {
        return Room.databaseBuilder(
            context,
            Class1RoomDatabase::class.java, "favorites_db"
        ).build()
    }
}