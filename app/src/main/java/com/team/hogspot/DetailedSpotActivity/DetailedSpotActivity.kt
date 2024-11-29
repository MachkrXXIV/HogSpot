package com.team.hogspot.DetailedSpotActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.composables.DetailedSpotCard
import com.team.hogspot.composables.Difficulty
import com.team.hogspot.composables.H1
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Hogspot
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.P
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.ui.theme.AppTheme




@Preview(showBackground = true)
@Composable
fun DetailedSpotPreview() {
    AppTheme {
        DetailedSpotPage()
    }
}

@Composable
fun DetailedSpotPage (

) {

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
    val hogspot = spots[0]
    val hogspotTitle = hogspot.title

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background) // Dark background color
            .padding(AppTheme.size.medium)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),

            ) {
            Header(
                pageTitle = "Spot",
                username = "Jordi",
                showBackButton = true,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(660.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                DetailedSpotCard(
                    hogspot = hogspot,
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "More like $hogspotTitle:",
                )

                Spacer(modifier = Modifier.height(12.dp))

                SpotCarousel(
                    spots = spots
                )
            }

            Navbar(
                activePage = "Search",
            )

        }
    }
}