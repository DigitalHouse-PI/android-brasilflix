package com.grupo7.brflixapp.data.api.util

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int): Command()
}