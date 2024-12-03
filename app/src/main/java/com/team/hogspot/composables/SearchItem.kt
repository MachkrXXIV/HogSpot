package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.R
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.ui.theme.AppTheme
import com.team.hogspot.ui.theme.blue500
import java.time.LocalDateTime

@Composable
fun SearchItem(
    geospot: GeoSpot,
    onClick: () -> Unit
) {
    val formattedDate = formatSpotDate(geospot.creationDate)

    val imageId = when (geospot.imgFilePath) {
        "spot_image1" -> R.drawable.spot_image1
        "spot_image2" -> R.drawable.spot_image2
        "spot_image3" -> R.drawable.spot_image3
        "spot_image4" -> R.drawable.spot_image4
        "spot_image5" -> R.drawable.spot_image5
        else -> R.drawable.spot_image1
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick) // Make the entire item clickable
            .padding(horizontal = AppTheme.size.normal)
            .height(64.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Space between the left content and the arrow
        ) {
            // Left content: Image, Title, Date, Difficulty, Star Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f) // Take up remaining space
            ) {
                // Image
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(AppTheme.shape.containerSmall)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                // Title and Date
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    P(
                        text = geospot.name
                    )
                    LabelSmall(
                        text = formattedDate,
                        color = AppTheme.colorScheme.textSecondary
                    )
                }

                // Difficulty
                DifficultyTag(
                    difficulty = geospot.difficulty
                )

                // Star rating
                StarRating(
                    rating = geospot.rating
                )
            }

            // Right content: More arrow
            Image(
                painter = painterResource(id = R.drawable.arrow_more_icon),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

fun formatSpotDate(date: LocalDateTime): String {
    // date format: "YYYY-MM-DDThh:mm:ss"
    // format to "MM.DD.YYYY"
    val month = date.monthValue.toString().padStart(2, '0')
    val day = date.dayOfMonth.toString().padStart(2, '0')
    val year = date.year.toString()
    return "$month.$day.$year"
}

@Composable
fun DifficultyTag(
    difficulty: Difficulty,
    onClick: (String) -> Unit = {},
    isCurrSelection: Boolean = false,
    size: DifficultyAndRatingSize = DifficultyAndRatingSize.SM
) {
    // if isCurrSelection: add a blue border-2dp around the tag
    val backgroundDifficulty = when (difficulty) {
        Difficulty.EASY -> AppTheme.colorScheme.difficultyEasy
        Difficulty.MEDIUM -> AppTheme.colorScheme.difficultyMedium
        Difficulty.HARD -> AppTheme.colorScheme.difficultyHard
    }
    val buttonWidth = when (size) {
        DifficultyAndRatingSize.XS -> 48.dp
        DifficultyAndRatingSize.SM -> 54.dp
    }
    val buttonHeight = when (size) {
        DifficultyAndRatingSize.XS -> 28.dp
        DifficultyAndRatingSize.SM -> 32.dp
    }
    val borderModifier = if (isCurrSelection) {
        Modifier.border(2.dp, blue500, AppTheme.shape.tag)
    } else {
        Modifier
    }
        Box(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
            .clip(AppTheme.shape.tag)
            .background(backgroundDifficulty)
            .then(borderModifier)
            .clickable(onClick = { onClick(difficulty.name) }),

        contentAlignment = Alignment.Center,
    ) {
        LabelSmall(
            text = difficulty.name,
        )
    }
}

@Composable
fun StarRating(rating: Double, size: DifficultyAndRatingSize = DifficultyAndRatingSize.SM) {
    val starSize = when (size) {
        DifficultyAndRatingSize.XS -> 14.dp
        DifficultyAndRatingSize.SM -> 16.dp
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val fullStars = rating.toInt()

        for (i in 0 until fullStars) {
            Image(
                painter = painterResource(id = R.drawable.star_icon),
                contentDescription = "Full Star",
                modifier = Modifier.size(starSize)
            )
        }
    }
}

@Composable
fun SelectDifficulty (
    onSelection : (Difficulty) -> Unit
) {
    var selectedDifficulty by remember { mutableStateOf<Difficulty>(Difficulty.EASY) }

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ){
        DifficultyTag(
            difficulty = Difficulty.EASY,
            onClick = {
                onSelection(Difficulty.EASY)
                selectedDifficulty = Difficulty.EASY
            },
            isCurrSelection = selectedDifficulty == Difficulty.EASY
        )
        DifficultyTag(
            difficulty = Difficulty.MEDIUM,
            onClick = {
                onSelection(Difficulty.MEDIUM)
                selectedDifficulty = Difficulty.MEDIUM
            },
            isCurrSelection = selectedDifficulty == Difficulty.MEDIUM
        )
        DifficultyTag(
            difficulty = Difficulty.HARD,
            onClick = {
                onSelection(Difficulty.HARD)
                selectedDifficulty = Difficulty.HARD
            },
            isCurrSelection = selectedDifficulty == Difficulty.HARD
        )
    }
}



//@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPrimaryButton() {
    val geospot1 = GeoSpot(
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
    val geospot2 = GeoSpot(
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
    )
    val geospot3 = GeoSpot(
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
    )
    val geospot4 = GeoSpot(
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
    )
    val geospot5 = GeoSpot(
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
    )

    AppTheme {
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.background)
                .padding(AppTheme.size.medium)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            SearchItem(
                geospot = geospot1,
                onClick = {}
            )
            SearchItem(
                geospot = geospot2,
                onClick = {}
            )
            SearchItem(
                geospot = geospot3,
                onClick = {}
            )

            SelectDifficulty(
                onSelection = { difficulty -> }
            )

            DifficultyAndRating(
                difficulty = Difficulty.MEDIUM,
                rating = 3.0,
                size = DifficultyAndRatingSize.SM
            )

            DifficultyAndRating(
                difficulty = Difficulty.MEDIUM,
                rating = 3.0,
                size = DifficultyAndRatingSize.XS
            )
        }
    }
}
