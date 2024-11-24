package com.team.hogspot.model.user

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    suspend fun getUserById(id: Int): Flow<Map<Int,User>> {
        return userDao.getUserById(id)
    }

    @WorkerThread
    suspend fun getAllFriends(friendIds: List<String>): Flow<List<User>> {
        return userDao.getFriendsFromUser(friendIds)
    }

    @WorkerThread
    suspend fun update(user: User){
        userDao.update(user)
    }

    @WorkerThread
    suspend fun delete(user: User){
        userDao.delete(user.userId)
    }
}