package com.grupo7.brasilflixapp.fragments.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.model.films.films
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccessTopRated: MutableLiveData<List<films>> =
        MutableLiveData()
    val onSuccessTopRated: LiveData<List<films>>
        get() = _onSuccessTopRated

    private val _onErrorTopRated: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorTopRated: LiveData<Int>
        get() = _onErrorTopRated

    private val _onCustomErrorTopRated: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorTopRated: LiveData<Boolean>
        get() = _onCustomErrorTopRated

    private val _onSuccessUpcoming: MutableLiveData<List<films>> =
        MutableLiveData()
    val onSuccessUpcoming: LiveData<List<films>>
        get() = _onSuccessUpcoming

    private val _onErrorUpcoming: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorUpcoming: LiveData<Int>
        get() = _onErrorUpcoming

    fun getTopRatedMovies() {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getTopRatedMovies() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessTopRated.postValue(
                        result?.filterIsInstance<films>()
                    )
                },
                onError = {
                    _onCustomErrorTopRated.postValue(true)
                }
            )
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getUpcomingMovies() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessUpcoming.postValue(
                        result?.filterIsInstance<films>()
                    )
                }
            )
        }
    }

}