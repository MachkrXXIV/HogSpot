package com.team.hogspot.model.geospot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoSpotDao {

    @MapInfo(keyColumn = "geoSpotId")
    @Query("SELECT * FROM geo_spot_table ORDER BY geoSpotId ASC")
    fun getAllGeoSpots(): Flow<Map<Int,GeoSpot>>

    @Update
    suspend fun update(geoSpot: GeoSpot)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(geoSpot: GeoSpot)

    @Query("DELETE FROM geo_spot_table")
    suspend fun deleteAll()

    @Query("DELETE FROM geo_spot_table WHERE geoSpotId = :id")
    suspend fun delete(id: Long)
}