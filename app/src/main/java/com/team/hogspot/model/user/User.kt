package com.team.hogspot.model.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long?,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "friends") val friends: List<String>, // list of user ids
    @ColumnInfo(name = "dateJoined") val dateJoined: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "streak") val streak: Int = 0,
    @ColumnInfo(name = "numSpots") val numSpots: Int = 0
)
