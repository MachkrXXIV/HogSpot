package com.team.hogspot.model.relations

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "geoSpotId"])
data class UserGeoSpotCrossRef(
    val userId: Long,
    val geoSpotId: Long
)
