package com.grupo7.brflixapp.data.api.main

import com.google.firebase.BuildConfig
import com.grupo7.brflixapp.data.api.endpoint.movies.EndpointMovies
import com.grupo7.brflixapp.data.api.endpoint.search.EndpointSearch
import com.grupo7.brflixapp.data.api.endpoint.series.EndpointSeries
import com.grupo7.brflixapp.util.constants.Constants.Api.API_CONST
import com.grupo7.brflixapp.util.constants.Constants.Api.API_KEY_TMDB
import com.grupo7.brflixapp.util.constants.Constants.Api.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.grupo7.brflixapp.util.constants.Constants.Api.QUERY_PARAM_LANGUAGE_LABEL
import com.grupo7.brflixapp.util.constants.Constants.Api.QUERY_PARAM_LANGUAGE_VALUE
import com.grupo7.brflixapp.util.constants.Constants.Api.QUERY_PARAM_REGION_LABEL
import com.grupo7.brflixapp.util.constants.Constants.Api.QUERY_PARAM_REGION_VALUE

class RetrofitInstance {
    companion object {

        val tmdbApiSeries: EndpointSeries = getRetrofitInstance().create(EndpointSeries::class.java)
        val tmdbApiMovies: EndpointMovies = getRetrofitInstance().create(EndpointMovies::class.java)
        val tmdbApiSearch: EndpointSearch = getRetrofitInstance().create(EndpointSearch::class.java)

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getInterceptorClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            val interceptor = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val url = chain.request().url.newBuilder()
                        .addQueryParameter(API_CONST, API_KEY_TMDB)
                        .addQueryParameter(QUERY_PARAM_LANGUAGE_LABEL, QUERY_PARAM_LANGUAGE_VALUE)
                        .addQueryParameter(QUERY_PARAM_REGION_LABEL, QUERY_PARAM_REGION_VALUE)
                        .build()
                    val newRequest = chain.request().newBuilder().url(url).build()
                    chain.proceed(newRequest)
                }
            return interceptor.build()
        }




    }
}