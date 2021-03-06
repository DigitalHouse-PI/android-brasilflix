package com.grupo7.brflixapp.data.database.series.allseries.dao

import androidx.room.*
import com.grupo7.brflixapp.data.database.series.allseries.entity.allseries

@Dao
interface AllSeriesDao {

    @Query("SELECT * FROM allseries")
    suspend fun getAllSeries(): List<allseries>

    @Query("SELECT * FROM allseries WHERE serieId = :serieId")
    suspend fun loadAllSeriesById(serieId: Int): allseries

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllallseries(allseriesList: List<allseries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSeries(allseries: allseries)

    @Delete
    suspend fun delete(allseries: allseries)
}