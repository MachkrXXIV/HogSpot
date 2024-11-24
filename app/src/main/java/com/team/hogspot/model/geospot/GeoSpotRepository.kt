package com.team.hogspot.model.geospot

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class GeoSpotRepository(private val geoSpotDao: GeoSpotDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allGeoSpots: Flow<Map<Int,GeoSpot>> = geoSpotDao.getAllGeoSpots()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(geoSpot: GeoSpot) {
        geoSpotDao.insert(geoSpot)
    }

    @WorkerThread
    suspend fun update(geoSpot: GeoSpot){
        geoSpotDao.update(geoSpot)
    }

    @WorkerThread
    suspend fun delete(geoSpot: GeoSpot){
        geoSpotDao.delete(geoSpot.geoSpotId!!)
    }
}