package com.team.hogspot.UserActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.composables.UserHeader
import com.team.hogspot.composables.UserInfoCards
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.user.User
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                UserPage(
                    userId = "1"
                )
            }
        }
    }

    // private fun
}


@Preview(showBackground = true)
@Composable
fun UserPreview() {
    AppTheme {
        UserPage(
            userId = "1",
            navController = null
        )
    }
}

@Composable
fun UserScreen(
    id: String,
    createdSpot: String,
    navController: NavController
) {
    UserPage(
        userId = id,
        createdSpot = createdSpot,
        navController = navController
    )
}


@Composable
fun UserPage(
    userId: String,
    createdSpot: String? = "false",
    navController: NavController? = null
) {
    val user = User(
        userId = 1,
        userName = "Jordi Castro",
        email = "jordi@gmail.com",
        dateJoined = LocalDateTime.now(),
        streak = 3,
        numSpots = 5,
        friends = listOf(
        )
    )

    val friends = mutableListOf<User>(
        User(
            userId = 1,
            userName = "Bob",
            email = "bob@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 4,
            numSpots = 2,
            friends = listOf()
        ),
        User(
            userId = 2,
            userName = "Kevin",
            email = "kevin@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 0,
            numSpots = 1,
            friends = listOf()
        ),
        User(
            userId = 1,
            userName = "Stuart",
            email = "stuart@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 17,
            numSpots = 14,
            friends = listOf()
        ),
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
                pageTitle = "User",
                username = "Jordi",
                showUserProfile = false,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(670.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                UserHeader(
                    username = user.userName,
                    dateJoined = user.dateJoined.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()
                )

                Spacer(modifier = Modifier.height(36.dp))

                UserInfoCards(
                    user = user
                )

                Spacer(modifier = Modifier.height(36.dp))

                H2(
                    text="Your Spots"
                )
                Spacer(modifier = Modifier.height(16.dp))

                SpotCarousel(
                    spots = spots,
                    onSpotClick = { spot ->
                        navController?.navigate(Screen.DetailedSpotScreen.withArgs(spot.geoSpotId.toString()))
                    }
                )

                Spacer(modifier = Modifier.height(36.dp))

                SecondaryButton(
                    onClick = {
                         navController?.navigate(Screen.NewSpotScreen.withArgs(userId))
                    },
                    text = "New HogSpot",
                    iconId = R.drawable.plus_icon,
                    iconColor = AppTheme.colorScheme.primary,
                    shape = AppTheme.shape.container,
                    modifier = Modifier
                        .height(64.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

            }

            Navbar(
                activePage = "Your Spots",
                navController = navController,
                userId = userId
            )

        }
    }

}