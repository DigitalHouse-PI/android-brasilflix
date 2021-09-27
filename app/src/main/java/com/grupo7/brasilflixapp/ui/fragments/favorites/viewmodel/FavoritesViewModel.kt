package com.grupo7.brasilflixapp.ui.fragments.favorites.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.database.favorites.model.Favorites
import com.grupo7.brasilflixapp.ui.fragments.favorites.usecase.FavoritesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application
): BaseViewModel(application) {

    private val favoritesUseCase = FavoritesUseCase(getApplication<Application>())

    private val _onSuccessFavoritesMoviesFromDb: MutableLiveData<List<Favorites>> = MutableLiveData()
    val onSuccessFavoritesMoviesFromDb: LiveData<List<Favorites>>
        get() = _onSuccessFavoritesMoviesFromDb

    fun getFavoritesMovieFromDb() {
        viewModelScope.launch {
            val movieFromDb = favoritesUseCase.getFavoritesMovieFromDb()
            _onSuccessFavoritesMoviesFromDb.postValue(movieFromDb)
        }
    }

    fun removeFavoritesMovieDb(favorites: Favorites) {
        viewModelScope.launch{
            favoritesUseCase.removeFavoritesMovieDb(favorites)
        }

    }

}