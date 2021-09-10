package com.grupo7.brasilflixapp.ui.fragments.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.detail.usecase.DetailUseCase
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.reviews.ReviewsUser
import com.grupo7.brasilflixapp.model.series.Series
import kotlinx.coroutines.launch

class DetailViewModel: BaseViewModel() {

    private val detailUseCase = DetailUseCase()

    private val _onSuccessMovieById: MutableLiveData<films> = MutableLiveData()
    val onSuccessMovieById: LiveData<films>
        get() = _onSuccessMovieById

    private val _onSuccessSeriesById: MutableLiveData<Series> = MutableLiveData()
    val onSuccessSeriesById: LiveData<Series>
        get() = _onSuccessSeriesById

    private val _onSuccessReviewsMovies: MutableLiveData<List<ReviewsUser>> = MutableLiveData()
    val onSuccessReviewsMovies: LiveData<List<ReviewsUser>>
        get() = _onSuccessReviewsMovies

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getMovieById(movieId) },
                onSuccess = {
                    _onSuccessMovieById.postValue(it as? films)
                }
            )
        }
    }
    fun getSeriesById(serieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getSeriesById(serieId) },
                onSuccess = {
                    _onSuccessSeriesById.postValue(it as? Series)
                }
            )
        }
    }

    fun getReviewsMovies(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailUseCase.getReviewsMovies(movieId) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessReviewsMovies.postValue(
                        result?.filterIsInstance<ReviewsUser>()
                    )
                }
            )
        }
    }


}