package com.grupo7.brasilflixapp.ui.fragments.home.viewmodel

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.home.paging.HomeDataSourceFactory
import com.grupo7.brasilflixapp.ui.fragments.home.paging.HomePageKeyedDataSource
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()
    private val homeRepository = HomeRepository()

//    <---------------------------------------------------- Setup Page 2 Home -------------------------------------->

    var topRatedPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSource = HomePageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactory = HomeDataSourceFactory(homePageKeyedDataSource)

        watchMoviesLiveDataSource = homeDataSourceFactory.getLiveDataSource()
        topRatedPagedList = LivePagedListBuilder(homeDataSourceFactory, pagedListConfig)
            .build()

    }

}