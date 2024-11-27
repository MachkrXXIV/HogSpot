package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.text.Selection
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.R
import com.team.hogspot.ui.theme.AppTheme
import com.team.hogspot.ui.theme.blue500


data class Hogspot(
    val id: Int,
    val title: String,
    val description: String,
    val location: String,
    val date: String,
    val imageUrls: String,
    val rating: Float,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}


@Composable
fun SearchItem(
    hogspot: Hogspot,
    onClick: () -> Unit
) {
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
                    painter = painterResource(id = R.drawable.map_icon),
                    contentDescription = "Map Icon",
                    modifier = Modifier.size(40.dp)
                )

                // Title and Date
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    P(
                        text = hogspot.title
                    )
                    LabelSmall(
                        text = hogspot.date,
                        color = AppTheme.colorScheme.textSecondary
                    )
                }

                // Difficulty
                DifficultyTag(
                    difficulty = hogspot.difficulty
                )

                // Star rating
                StarRating(
                    rating = hogspot.rating
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

@Composable
fun DifficultyTag(
    difficulty: Difficulty,
    onClick: (String) -> Unit = {},
    isCurrSelection: Boolean = false
) {
    // if isCurrSelection: add a blue border-2dp around the tag
    val backgroundDifficulty = when (difficulty) {
        Difficulty.EASY -> AppTheme.colorScheme.difficultyEasy
        Difficulty.MEDIUM -> AppTheme.colorScheme.difficultyMedium
        Difficulty.HARD -> AppTheme.colorScheme.difficultyHard
    }
    val borderModifier = if (isCurrSelection) {
        Modifier.border(2.dp, blue500, AppTheme.shape.tag)
    } else {
        Modifier
    }
        Box(
        modifier = Modifier
            .width(54.dp)
            .height(32.dp)
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
fun StarRating(rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val fullStars = rating.toInt()

        for (i in 0 until fullStars) {
            Image(
                painter = painterResource(id = R.drawable.star_icon),
                contentDescription = "Full Star",
                modifier = Modifier.size(16.dp)
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
    val hogspot = Hogspot(
        id = 1,
        title = "Title1",
        description = "Description1",
        location = "Location1",
        date = "12.2.2024",
        imageUrls = "ImageUrls1",
        rating = 3.0f,
        difficulty = Difficulty.EASY
    )

    val hogspot2 = Hogspot(
        id = 2,
        title = "Title2",
        description = "Description2",
        location = "Location2",
        date = "12.2.2024",
        imageUrls = "ImageUrls2",
        rating = 4.0f,
        difficulty = Difficulty.MEDIUM
    )

    val hogspot3 = Hogspot(
        id = 3,
        title = "Title3",
        description = "Description3",
        location = "Location3",
        date = "12.2.2024",
        imageUrls = "ImageUrls3",
        rating = 5.0f,
        difficulty = Difficulty.HARD
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
                hogspot = hogspot,
                onClick = {}
            )
            SearchItem(
                hogspot = hogspot2,
                onClick = {}
            )
            SearchItem(
                hogspot = hogspot3,
                onClick = {}
            )

            SelectDifficulty(
                onSelection = { difficulty -> }
            )
        }
    }
}
