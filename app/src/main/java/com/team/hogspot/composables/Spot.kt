package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime

@Composable
fun SpotCard(
    spot: GeoSpot,
    onSpotClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onSpotClick() }
    ) {
        // TODO: style the spotCard
    }
}


@Composable
fun SpotCarousel(
    spots: List<GeoSpot>,
    onSpotClick: (GeoSpot) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(AppTheme.shape.container)
            .background(AppTheme.colorScheme.backgroundSecondary)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            spots.forEach { spot ->
                SpotCard(
                    spot = spot,
                    onSpotClick = { onSpotClick(spot) }
                )
            }
        }
    }
}








@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun SpotPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.background)
                .padding(AppTheme.size.medium)
                .fillMaxSize(),


            ) {
                SpotCarousel(
                    spots = listOf(
                        GeoSpot(
                            geoSpotId = 1,
                            name = "HogSpot 1",
                            description = "This is a description of HogSpot 1",
                            latitude = 0.0,
                            longitude = 0.0,
                            creatorId = 1,
                            creationDate = LocalDateTime.now(),
                            imgFilePath = "img1.jpg",
                            difficulty = Difficulty.EASY,
                            hint = "This is a hint for HogSpot 1",
                            rating = 3.0,

                        ),
                        GeoSpot(
                            geoSpotId = 2,
                            name = "HogSpot 2",
                            description = "This is a description of HogSpot 2",
                            latitude = 0.0,
                            longitude = 0.0,
                            creatorId = 2,
                            creationDate = LocalDateTime.now(),
                            imgFilePath = "img2.jpg",
                            difficulty = Difficulty.MEDIUM,
                            hint = "This is a hint for HogSpot 2",
                            rating = 4.0,
                        ),
                    )
                )
        }
    }
}
