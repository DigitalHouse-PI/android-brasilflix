package com.lucasesteves.brasilflixapp.util.constants

import java.util.*


class Constants {
    object Api {
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_REGION_LABEL = "region"
        const val QUERY_PARAM_REGION_VALUE = "BR"

        fun queryParamLanguageValue(): String {
            return if (Locale.getDefault().isO3Country == "BRA") "pt-BR"
            else "en-US"
        }
    }
}