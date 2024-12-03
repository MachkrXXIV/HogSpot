package com.team.hogspot.DetailedSpotActivity

import android.util.Log
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
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.composables.DetailedSpotCard
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.user.User
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime


@Preview(showBackground = true)
@Composable
fun DetailedSpotPreview() {
    AppTheme {
        DetailedSpotPage(userId = "1")
    }
}

@Composable
fun DetailedSpotScreen(
    id: String,
    navController: NavController
) {
    DetailedSpotPage(
        userId = id,
        onPlayClick = { spotId ->
            Log.d("DetailedSpotScreen", "Play button clicked for spot $spotId")
             navController.navigate(Screen.PlayScreen.route + "/$spotId")
        },
        onUserClick = {
            navController.navigate(Screen.UserScreen.withArgs(id, "false"))
        },
        navController = navController
    )
}


@Composable
fun DetailedSpotPage (
    userId: String,
    navController: NavController? = null,
    onPlayClick: (String) -> Unit = {},
    onUserClick: () -> Unit = {}
) {

    val user = User(
        userId = 1,
        userName = "Bob",
        email = "bob@gmail.com",
        dateJoined = LocalDateTime.now(),
        streak = 4,
        numSpots = 2,
        friends = listOf()
    )

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
    val geospot = spots[0]
    val geospotName = geospot.name

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(AppTheme.size.medium)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),

            ) {
            Header(
                pageTitle = "Spot",
                username = user.userName,
                showBackButton = true,
                onBackClick = {
                    navController?.popBackStack()
                },
                onUserClick = onUserClick
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
                    geospot = geospot,
                    onPlayClick = { onPlayClick(geospot.geoSpotId.toString()) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "More like $geospotName:",
                )

                Spacer(modifier = Modifier.height(12.dp))

                SpotCarousel(
                    spots = spots,
                    onSpotClick = { geospot ->
                        navController?.navigate(Screen.DetailedSpotScreen.withArgs(geospot.geoSpotId.toString()))
                    }
                )
            }

            Navbar(
                activePage = "Search",
                navController = navController,
                userId = userId
            )

        }
    }
}