package com.team.hogspot.model.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long?,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "friends") val friends: List<String> // list of user ids
)
