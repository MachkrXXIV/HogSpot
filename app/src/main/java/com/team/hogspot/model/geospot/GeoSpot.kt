package com.team.hogspot.model.geospot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.team.hogspot.model.user.User
import java.time.LocalDateTime

@Entity(
    tableName = "geo_spot_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["creatorId"],
        onDelete = CASCADE
    )]
)
data class GeoSpot(
    @PrimaryKey(autoGenerate = true) val geoSpotId: Long?,
    @ColumnInfo(name = "creatorId") val creatorId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imgFilePath") val imgFilePath: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "difficulty") var difficulty: Difficulty,
    @ColumnInfo(name = "hint") val hint: String?,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "creationDate") val creationDate: LocalDateTime,
)