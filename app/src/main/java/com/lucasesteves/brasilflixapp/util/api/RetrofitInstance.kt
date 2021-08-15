package com.lucasesteves.brasilflixapp.util.api

import com.lucasesteves.brasilflixapp.BuildConfig
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.API_CONST
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.API_KEY_TMDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.QUERY_PARAM_LANGUAGE_LABEL
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.QUERY_PARAM_REGION_LABEL
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.QUERY_PARAM_REGION_VALUE
import com.lucasesteves.brasilflixapp.util.constants.Constants.Api.queryParamLanguageValue

class RetrofitInstance {
    companion object {
        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
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
                        .addQueryParameter(QUERY_PARAM_LANGUAGE_LABEL, queryParamLanguageValue())
                        .addQueryParameter(QUERY_PARAM_REGION_LABEL, QUERY_PARAM_REGION_VALUE)
                        .build()
                    val newRequest = chain.request().newBuilder().url(url).build()
                    chain.proceed(newRequest)
                }
            return interceptor.build()
        }




    }
}