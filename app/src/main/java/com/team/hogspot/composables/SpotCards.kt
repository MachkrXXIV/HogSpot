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
import androidx.navigation.NavController
import com.team.hogspot.R
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

enum class DifficultyAndRatingSize {
    XS, SM
}

@Composable
fun HogSpotCard(
    hogspot: Hogspot,
    onSpotClick: () -> Unit = {},
) {
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
                painter = painterResource(id = R.drawable.spot_image1),
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
                text = hogspot.title,
            )
        }
        Spacer(modifier = Modifier.height(AppTheme.size.small))
        DifficultyAndRating(
            difficulty = hogspot.difficulty,
            rating = hogspot.rating,
            size = DifficultyAndRatingSize.XS
        )
    }
}


@Composable
fun SpotCarousel(
    spots: List<Hogspot>,
    onSpotClick: (Hogspot) -> Unit = {},
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
            spots.forEach { hogspot ->
                HogSpotCard(
                    hogspot = hogspot,
                    onSpotClick = { onSpotClick(hogspot) }
                )
            }
        }
    }
}

@Composable
fun DetailedSpotCard(
    hogspot: Hogspot,
    onPlayClick: () -> Unit = {}

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
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
                text = hogspot.title
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            // make the text centered start
            contentAlignment = Alignment.TopStart

        ) {
            P(
                text = hogspot.description,
                color = AppTheme.colorScheme.textSecondary,
                textAlignment = TextAlign.Start
            )

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        DifficultyAndRating(
            difficulty = hogspot.difficulty,
            rating = hogspot.rating
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
fun DifficultyAndRating(difficulty: Difficulty, rating: Float, size: DifficultyAndRatingSize = DifficultyAndRatingSize.SM) {
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
        Hogspot(
            id = 1,
            title = "Title1",
            description = "Description1",
            location = "Location1",
            date = "12.2.2024",
            imageUrls = "ImageUrls1",
            rating = 3.0f,
            difficulty = Difficulty.EASY
        ),
        Hogspot(
            id = 2,
            title = "Title2",
            description = "Description2",
            location = "Location2",
            date = "12.2.2024",
            imageUrls = "ImageUrls2",
            rating = 4.0f,
            difficulty = Difficulty.MEDIUM
        ),
        Hogspot(
            id = 3,
            title = "Title3",
            description = "Description3",
            location = "Location3",
            date = "12.2.2024",
            imageUrls = "ImageUrls3",
            rating = 5.0f,
            difficulty = Difficulty.HARD
        )
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
                hogspot = Hogspot(
                    id = 1,
                    title = "Title1",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    location = "Location1",
                    date = "12.2.2024",
                    imageUrls = "ImageUrls1",
                    rating = 3.0f,
                    difficulty = Difficulty.EASY
                )
            )
        }
    }
}
