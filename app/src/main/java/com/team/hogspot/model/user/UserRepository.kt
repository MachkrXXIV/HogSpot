package com.team.hogspot.model.user

import androidx.annotation.WorkerThread
import com.team.hogspot.model.geospot.GeoSpot
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    @WorkerThread
    suspend fun getUserById(id: Long): Flow<Map<Long,User>> {
        return userDao.getUserById(id)
    }

    @WorkerThread
    suspend fun getAllFriends(friendIds: List<String>): Flow<List<User>> {
        return userDao.getFriendsFromUser(friendIds)
    }

    @WorkerThread
    suspend fun getSavedGeoSpotsFromUser(id: Long): Flow<GeoSpot> {
        return userDao.getSavedGeoSpotsFromUser(id)
    }

    @WorkerThread
    suspend fun update(user: User){
        userDao.update(user)
    }

    @WorkerThread
    suspend fun delete(user: User){
        userDao.delete(user.userId!!)
    }
}