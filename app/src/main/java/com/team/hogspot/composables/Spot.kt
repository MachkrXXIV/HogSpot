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
import com.team.hogspot.ui.theme.AppTheme


data class SpotTemp(
    val id: Int,
    val name: String,
    val description: String,
    val difficulty: String,
    val tags: List<String>,
    val rating: Double,
    val numRatings: Int,
    val numCheckins: Int,
    val numPhotos: Int,
    val numComments: Int,
    val photos: List<String>,
)

@Composable
fun SpotCard(
    spot: SpotTemp,
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
    spots: List<SpotTemp>,
    onSpotClick: (SpotTemp) -> Unit = {},
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
                        SpotTemp(
                            id = 1,
                            name = "Spot1",
                            description = "Description1",
                            difficulty = "Easy",
                            tags = listOf("Tag1", "Tag2"),
                            rating = 3.0,
                            numRatings = 10,
                            numCheckins = 5,
                            numPhotos = 3,
                            numComments = 2,
                            photos = listOf("Photo1", "Photo2")
                        ),
                        SpotTemp(
                            id = 2,
                            name = "Spot2",
                            description = "Description2",
                            difficulty = "Medium",
                            tags = listOf("Tag1", "Tag2"),
                            rating = 4.0,
                            numRatings = 10,
                            numCheckins = 5,
                            numPhotos = 3,
                            numComments = 2,
                            photos = listOf("Photo1", "Photo2")
                        ),
                        SpotTemp(
                            id = 3,
                            name = "Spot3",
                            description = "Description3",
                            difficulty = "Hard",
                            tags = listOf("Tag1", "Tag2"),
                            rating = 5.0,
                            numRatings = 10,
                            numCheckins = 5,
                            numPhotos = 3,
                            numComments = 2,
                            photos = listOf("Photo1", "Photo2")
                        )
                    )
                )
        }
    }
}
