package com.grupo7.brasilflixapp.ui.fragments.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brasilflixapp.model.films.films

class HomeDataSourceFactory(
    private val tmdbDataSource: HomePageKeyedDataSource
): DataSource.Factory<Int, films>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, films>>()
    override fun create(): DataSource<Int, films> {
        tmdbLiveDataSource.postValue(tmdbDataSource)
        return tmdbDataSource
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, films>> {
        return tmdbLiveDataSource
    }
}