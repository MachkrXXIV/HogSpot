package com.team.hogspot

import android.app.Application
import com.team.hogspot.model.AppRoomDatabase
import com.team.hogspot.model.geospot.GeoSpotRepository
import com.team.hogspot.model.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppRoomDatabase.getDatabase(this, applicationScope) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val geoSpotRepository by lazy { GeoSpotRepository(database.geoSpotDao()) }
}