package com.grupo7.brflixapp.ui.fragments.series.paging.seriestoprated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.grupo7.brflixapp.ui.model.series.Series

class DataSourceFactorySeriesTopRated (
    private val tmdbDataSourceSeriesTopRated: PageKeyedDataSourceSeriesTopRated
): DataSource.Factory<Int, Series>() {

    private val tmdbLiveDataSourceTopRated = MutableLiveData<PageKeyedDataSource<Int, Series>>()
    override fun create(): DataSource<Int, Series> {
        tmdbLiveDataSourceTopRated.postValue(tmdbDataSourceSeriesTopRated)
        return tmdbDataSourceSeriesTopRated
    }

    fun getLiveDataSourceTopRated() : MutableLiveData<PageKeyedDataSource<Int, Series>> {
        return tmdbLiveDataSourceTopRated
    }
}