package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.R
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime

enum class DifficultyAndRatingSize {
    XS, SM
}

@Composable
fun SpotCard(
    geospot: GeoSpot,
    onSpotClick: () -> Unit = {},
) {
    val imageId = when (geospot.imgFilePath) {
        "spot_image1" -> R.drawable.spot_image1
        "spot_image2" -> R.drawable.spot_image2
        "spot_image3" -> R.drawable.spot_image3
        "spot_image4" -> R.drawable.spot_image4
        "spot_image5" -> R.drawable.spot_image5
        else -> R.drawable.spot_image1
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(170.dp)
            .clickable { onSpotClick() }
            .clip(AppTheme.shape.container)
            .background(AppTheme.colorScheme.onBackground)
            .padding(10.dp),
         // shape = AppTheme.shape.container
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(AppTheme.shape.container)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(AppTheme.size.small))

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp), // make it so the text does not overflow the card
        ) {
            P(
                text = geospot.name,
            )
        }
        Spacer(modifier = Modifier.height(AppTheme.size.small))
        DifficultyAndRating(
            difficulty = geospot.difficulty,
            rating = geospot.rating,
            size = DifficultyAndRatingSize.XS
        )
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
            .height(200.dp)
            .clip(AppTheme.shape.container)
            .background(AppTheme.colorScheme.backgroundSecondary)
    ) {

        Row (
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small),

        ) {
            spots.forEach { geospot ->
                SpotCard(
                    geospot = geospot,
                    onSpotClick = { onSpotClick(geospot) }
                )
            }
        }
    }
}

@Composable
fun DetailedSpotCard(
    geospot: GeoSpot,
    onPlayClick: () -> Unit = {}

) {

    val formattedDate = formatSpotDate(geospot.creationDate)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
            .clip(AppTheme.shape.container)
            .background(AppTheme.colorScheme.backgroundSecondary)
            .padding(AppTheme.size.medium),
//        Arrangement.spacedBy(AppTheme.size.small)/
    ) {
        Image(
            painter = painterResource(id = R.drawable.spot_image1),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(AppTheme.shape.container)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            H1(
                text = geospot.name
            )
        }

        P(
            text = formattedDate,
            color = AppTheme.colorScheme.textSecondary,
            textAlignment = TextAlign.Start
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            // make the text centered start
            contentAlignment = Alignment.TopStart

        ) {
            P(
                text = geospot.description,
                color = AppTheme.colorScheme.textSecondary,
                textAlignment = TextAlign.Start
            )

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        DifficultyAndRating(
            difficulty = geospot.difficulty,
            rating = geospot.rating
        )

        Spacer(
            modifier = Modifier.height(AppTheme.size.large)
        )

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PrimaryButton(
                text = "Play",
                onClick = onPlayClick,
                modifier = Modifier
                    .width(200.dp),
                shape = AppTheme.shape.container
            )
        }

    }
}

@Composable
fun DifficultyAndRating(difficulty: Difficulty, rating: Double, size: DifficultyAndRatingSize = DifficultyAndRatingSize.SM) {
    val spacing = when (size) {
        DifficultyAndRatingSize.XS -> AppTheme.size.small
        DifficultyAndRatingSize.SM -> AppTheme.size.medium
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically

    ) {
        DifficultyTag(
            difficulty = difficulty,
            size = size
        )

        StarRating(
            rating = rating,
            size = size
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun SpotCardsPreview() {

    val spots = listOf(
        GeoSpot(
            geoSpotId = 1,
            creatorId = 1,
            name = "Title1",
            imgFilePath = "spot_image1",
            description = "Description1",
            difficulty = Difficulty.EASY,
            hint = "hint1",
            latitude = 1.0,
            longitude = 1.0,
            creationDate = LocalDateTime.of(2024, 12, 2, 10, 10),
            rating = 5.0
        ),
        GeoSpot(
            geoSpotId = 2,
            creatorId = 1,
            name = "Title2",
            imgFilePath = "spot_image2",
            description = "Description2",
            difficulty = Difficulty.MEDIUM,
            hint = "hint2",
            latitude = 1.0,
            longitude = 1.0,
            creationDate = LocalDateTime.of(2024, 4, 18, 12, 33),
            rating = 4.0
        ),
        GeoSpot(
            geoSpotId = 3,
            creatorId = 1,
            name = "Title3",
            imgFilePath = "spot_image3",
            description = "Description3",
            difficulty = Difficulty.HARD,
            hint = "hint3",
            latitude = 1.0,
            longitude = 1.0,
            creationDate = LocalDateTime.of(2024, 9, 21, 23, 11),
            rating = 3.0
        ),
        GeoSpot(
            geoSpotId = 4,
            creatorId = 1,
            name = "Title4",
            imgFilePath = "spot_image4",
            description = "Description4",
            difficulty = Difficulty.EASY,
            hint = "hint4",
            latitude = 1.0,
            longitude = 1.0,
            creationDate = LocalDateTime.of(2024, 6, 22, 4, 47),
            rating = 4.0
        ),
        GeoSpot(
            geoSpotId = 5,
            creatorId = 1,
            name = "Title5",
            imgFilePath = "spot_image5",
            description = "Description5",
            difficulty = Difficulty.MEDIUM,
            hint = "hint5",
            latitude = 1.0,
            longitude = 1.0,
            creationDate = LocalDateTime.of(2024, 3, 15, 15, 22),
            rating = 5.0
        ),
    )


    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.background)
                .padding(AppTheme.size.large)
                .fillMaxSize(),


            ) {
                SpotCarousel(
                    spots = spots
                )

            Spacer(modifier = Modifier.height(AppTheme.size.medium))

            DetailedSpotCard(
                geospot = GeoSpot(
                    geoSpotId = 1,
                    creatorId = 1,
                    name = "Title1",
                    imgFilePath = "spot_image1",
                    description = "Description1",
                    difficulty = Difficulty.EASY,
                    hint = "hint1",
                    latitude = 1.0,
                    longitude = 1.0,
                    creationDate = LocalDateTime.of(2024, 12, 2, 10, 10),
                    rating = 5.0
                )
            )
        }
    }
}
