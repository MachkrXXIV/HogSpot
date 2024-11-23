package com.team.hogspot.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.user.User

data class UserWithSavedGeoSpots(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "geoSpotId",
        associateBy = Junction(UserGeoSpotCrossRef::class)
    )
    val savedGeoSpots: List<GeoSpot>
)
