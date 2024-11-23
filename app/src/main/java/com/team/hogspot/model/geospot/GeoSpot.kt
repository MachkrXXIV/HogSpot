package com.team.hogspot.model.geospot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "geospot_table")
data class GeoSpot(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "filePath") val filepath: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "difficulty") var difficulty: String,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "created_by") val createdBy: LocalDateTime
)