package com.grupo7.brflixapp.ui.fragments.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brflixapp.base.BaseViewModel
import com.grupo7.brflixapp.data.database.favorites.entity.Favorites
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesDetails
import com.grupo7.brflixapp.data.database.favorites.entity.FavoritesSeries
import com.grupo7.brflixapp.ui.fragments.favorites.usecase.FavoritesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel(): BaseViewModel() {

    private val favoritesUseCase = FavoritesUseCase()

    private val _onSuccessFavoritesMoviesFromDb: MutableLiveData<List<Favorites>> = MutableLiveData()
    val onSuccessFavoritesMoviesFromDb: LiveData<List<Favorites>>
        get() = _onSuccessFavoritesMoviesFromDb

    private val _onSuccessFavoritesSeriesFromDb: MutableLiveData<List<FavoritesSeries>> = MutableLiveData()
    val onSuccessFavoritesSeriesFromDb: LiveData<List<FavoritesSeries>>
        get() = _onSuccessFavoritesSeriesFromDb

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

    fun getFavoritesSeriesFromDb() {
        viewModelScope.launch {
            val serieFromDb = favoritesUseCase.getFavoritesSeriesFromDb()
            _onSuccessFavoritesSeriesFromDb.postValue(serieFromDb)
        }
    }

    fun removeFavoritesSeriesDb(favorites: FavoritesSeries) {
        viewModelScope.launch{
            favoritesUseCase.removeFavoritesSeriesDb(favorites)
        }

    }

    fun removeFavoritesDetailsDb(favorites: FavoritesDetails) {
        viewModelScope.launch{
            favoritesUseCase.removeFavoritesDetailsDb(favorites)
        }

    }

}