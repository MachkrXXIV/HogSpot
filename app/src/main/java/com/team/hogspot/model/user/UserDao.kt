package com.team.hogspot.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.relations.UserWithSavedGeoSpots
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @MapInfo(keyColumn = "userId")
    @Query("SELECT * FROM user_table WHERE userId = :id")
    fun getUserById(id: Long): Flow<Map<Long, User>>

    @Query("SELECT * FROM user_table WHERE userId IN (:friendsIds)")
    fun getFriendsFromUser(friendsIds: List<String>): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :id")
    fun getSavedGeoSpotsFromUser(id: Long): Flow<UserWithSavedGeoSpots>

    @Update
    suspend fun update(geoPhoto: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(geoPhoto: User): Long

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("DELETE FROM user_table WHERE userId = :id")
    suspend fun delete(id: Long)
}