package com.team.hogspot.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @MapInfo(keyColumn = "id")
    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Int): Flow<Map<Int, User>>

//    @Query("SELECT * FROM user_table WHERE id = :id")
//    fun getFriendsFromUser(id: Int): Flow<List<String>>
    @Query("SELECT * FROM user_table WHERE id IN (:friendsIds)")
    fun getFriendsFromUser(friendsIds: List<String>): Flow<List<User>>

    @Update
    suspend fun update(geoPhoto: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(geoPhoto: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun delete(id: Int)
}