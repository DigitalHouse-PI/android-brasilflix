package com.grupo7.brasilflixapp.ui.fragments.home.viewmodel

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.grupo7.brasilflixapp.base.BaseViewModel
import com.grupo7.brasilflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brasilflixapp.model.films.films
import com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated.HomeDataSourceFactoryTopRated
import com.grupo7.brasilflixapp.ui.fragments.home.paging.TopRated.HomePageKeyedDataSourceTopRated
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.HomeDataSourceFactoryUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.paging.UpComing.HomePageKeyedDataSourceUpComing
import com.grupo7.brasilflixapp.ui.fragments.home.repository.HomeRepository

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()
    private val homeRepository = HomeRepository()

//    <---------------------------------------------------- Setup Page 2 Home - Top Rated-------------------------------------->

    var topRatedPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSource = HomePageKeyedDataSourceTopRated(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactory = HomeDataSourceFactoryTopRated(homePageKeyedDataSource)

        watchMoviesLiveDataSource = homeDataSourceFactory.getLiveDataSource()
        topRatedPagedList = LivePagedListBuilder(homeDataSourceFactory, pagedListConfig)
            .build()

    }

    //    <---------------------------------------------------- Setup Page 2 Home - Up Coming-------------------------------------->

    var upComingPagedList: LiveData<PagedList<films>>? = null
    private var watchMoviesLiveDataSourceUpComing: LiveData<PageKeyedDataSource<Int, films>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()


        val homePageKeyedDataSourceUpComing = HomePageKeyedDataSourceUpComing(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val homeDataSourceFactoryUpComing = HomeDataSourceFactoryUpComing(homePageKeyedDataSourceUpComing)

        watchMoviesLiveDataSourceUpComing = homeDataSourceFactoryUpComing.getLiveDataSource()
        upComingPagedList = LivePagedListBuilder(homeDataSourceFactoryUpComing, pagedListConfig)
            .build()

    }

}