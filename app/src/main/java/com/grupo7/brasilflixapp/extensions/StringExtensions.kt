package com.grupo7.brasilflixapp.extensions

fun String?.getFullImageUrl() = "https://image.tmdb.org/t/p/w500$this"

fun String.getDateBR(): String {
    val dateList = this.split("-")
    val year = dateList[0]
    val month = dateList[1]
    val day = dateList[2]
    val date = "$day-$month-$year"
    return date
}