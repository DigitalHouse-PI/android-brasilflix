package com.grupo7.brasilflixapp.util.constants

import java.util.*


class Constants {
    object Api {
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_REGION_LABEL = "region"
        const val QUERY_PARAM_REGION_VALUE = "BR"
        const val API_CONST = "api_key"
        const val API_KEY_TMDB = "50486dab40ae7909623d0d610e5e2bd6"
        const val BASE_URL = "https://api.themoviedb.org/3/"


        fun queryParamLanguageValue(): String {
            return if (Locale.getDefault().isO3Country == "BRA") "pt-BR"
            else "en-US"
        }
    }

    object Home {
        const val KEY_BUNDLE_MOVIE_ID = "movieId"
        const val KEY_BUNDLE_SERIE_ID = "serieId"
    }

}