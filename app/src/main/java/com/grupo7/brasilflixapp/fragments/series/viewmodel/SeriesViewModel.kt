package com.grupo7.brasilflixapp.fragments.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.fragments.series.usecase.SeriesUsecase
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.model.series.Series
import kotlinx.coroutines.launch

class SeriesViewModel : BaseViewModel() {

    private val seriesUseCase = SeriesUsecase()

    private val _onSuccessSeries: MutableLiveData<List<Series>> =
        MutableLiveData()
    val onSuccessTopRated: LiveData<List<Series>>
        get() = _onSuccessSeries

    private val _onErrorSeries: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorTopRated: LiveData<Int>
        get() = _onErrorSeries

    private val _onCustomErrorSeries: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorTopRated: LiveData<Boolean>
        get() = _onCustomErrorSeries

    fun getSeries() {
        viewModelScope.launch {
            callApi(
                suspend { seriesUseCase.getSeries() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessSeries.postValue(
                        result?.filterIsInstance<Series>()
                    )
                },
                onError = {
                    _onCustomErrorSeries.postValue(true)
                }
            )
        }
    }


}