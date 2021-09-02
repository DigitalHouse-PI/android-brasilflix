package com.grupo7.brasilflixapp.ui.fragments.popular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.popular.usecase.PopularUseCase
import com.grupo7.brasilflixapp.model.films.films
import kotlinx.coroutines.launch

class PopularViewModel : BaseViewModel() {

    private val popularUseCase = PopularUseCase()

    private val _onSuccessPopular: MutableLiveData<List<films>> =
        MutableLiveData()
    val onSuccessPopular: LiveData<List<films>>
        get() = _onSuccessPopular

    private val _onErrorPopular: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorPopular: LiveData<Int>
        get() = _onErrorPopular

    private val _onCustomErrorPopular: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorPopular: LiveData<Boolean>
        get() = _onCustomErrorPopular

    fun getPopular() {
        viewModelScope.launch {
            callApi(
                suspend { popularUseCase.getPopular() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessPopular.postValue(
                        result?.filterIsInstance<films>()
                    )
                },
                onError = {
                    _onCustomErrorPopular.postValue(true)
                }
            )
        }
    }



}