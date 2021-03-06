package com.grupo7.brflixapp.ui.fragments.series.paging.seriespopular

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brflixapp.ui.model.series.Series

class DataSourceFactorySeriesPopular (
    private val tmdbDataSourceSeriesPopular: PageKeyedDataSourceSeriesPopular
): DataSource.Factory<Int, Series>() {

    private val tmdbLiveDataSourcePopular = MutableLiveData<PageKeyedDataSource<Int, Series>>()
    override fun create(): DataSource<Int, Series> {
        tmdbLiveDataSourcePopular.postValue(tmdbDataSourceSeriesPopular)
        return tmdbDataSourceSeriesPopular
    }

    fun getLiveDataSourcePopular() : MutableLiveData<PageKeyedDataSource<Int, Series>> {
        return tmdbLiveDataSourcePopular
    }
}