package com.grupo7.brflixapp.ui.fragments.home.paging.TopRated

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.grupo7.brflixapp.data.api.util.ResponseApi
import com.grupo7.brflixapp.data.database.movies.toprated.database.TopRatedDatabase
import com.grupo7.brflixapp.data.database.movies.toprated.entity.tofilmsDb
import com.grupo7.brflixapp.ui.model.films.films
import com.grupo7.brflixapp.ui.model.films.filmsResults
import com.grupo7.brflixapp.ui.fragments.home.repository.HomeRepository
import com.grupo7.brflixapp.ui.fragments.home.usecase.HomeUseCase
import com.grupo7.brflixapp.util.constants.Constants.Home.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PageKeyedDataSourceTopRated(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PageKeyedDataSource<Int, films>(), KoinComponent {

    private val application: Application by inject()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, films>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<films> = getTopRatedMovies(FIRST_PAGE)
            homeUseCase.saveAllMoviesDatabase(movies)
            homeUseCase.saveTopRatedDatabase(movies)
            callback.onResult(movies, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, films>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, films>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, films>) {
        CoroutineScope(Dispatchers.IO).launch {
            val films: List<films> = getTopRatedMovies(page)
            homeUseCase.saveAllMoviesDatabase(films)
            homeUseCase.saveTopRatedDatabase(films)
            callback.onResult(films, nextPage)
        }

    }
    suspend fun getTopRatedMovies(page: Int): List<films>{
        return when (
            val response = homeRepository.getTopRatedMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? filmsResults
               return homeUseCase.setupTopRatedList(list)
            }
            is ResponseApi.Error -> {
                var topratedDB =  TopRatedDatabase
                    .getDatabase(application)
                    .topratedDao()
                    .getAllTopRated()

                return topratedDB.map {
                    it.tofilmsDb()
                }
            }
        }
    }

}