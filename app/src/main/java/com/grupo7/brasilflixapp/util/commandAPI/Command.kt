package com.grupo7.brasilflixapp.util.commandAPI

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int): Command()
}