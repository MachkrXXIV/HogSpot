package com.team.hogspot.model.relations

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "geoSpotId"])
data class UserGeoSpotCrossRef(
    val userId: Int,
    val geoSpotId: Int
)
