package com.team.hogspot.UserActivity

import android.os.Bundle
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.composables.Difficulty
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Hogspot
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.composables.UserHeader
import com.team.hogspot.composables.UserInfoCards
import com.team.hogspot.composables.UserTemp
import com.team.hogspot.ui.theme.AppTheme

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                //
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
    navController: NavController
) {
    UserPage(
        userId = id,
        navController = navController
    )
}


@Composable
fun UserPage(
    userId: String,
    navController: NavController? = null
) {

    val user = UserTemp(
        id = 1,
        username = "Jordi Castro",
        email = "jordi@gmail.com",
        dateJoined = "10/12/24",
        streak = 5,
        numSpots = 3,
        spots = listOf(
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
        ),
        friends = listOf(
            UserTemp(
                id = 1,
                username = "Bob",
                email = "bob@gmail.com",
                dateJoined = "10/12/24",
                streak = 4,
                numSpots = 2,
                spots = listOf(),
                friends = listOf()
                ),
            UserTemp(
                id = 2,
                username = "Kevin",
                email = "kevin@gmail.com",
                dateJoined = "10/12/24",
                streak = 0,
                numSpots = 1,
                spots = listOf(),
                friends = listOf()
            ),
            UserTemp(
                id = 1,
                username = "Stuart",
                email = "stuart@gmail.com",
                dateJoined = "10/12/2024",
                streak = 17,
                numSpots = 14,
                spots = listOf(),
                friends = listOf()
            ),
        )
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
                    username = user.username,
                    dateJoined = user.dateJoined
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
                    spots = user.spots,
                    onSpotClick = { hogspot ->
                        navController?.navigate(Screen.DetailedSpotScreen.withArgs(hogspot.id.toString()))
                    }
                )

                Spacer(modifier = Modifier.height(36.dp))

                SecondaryButton(
                    onClick = {
                         navController?.navigate(Screen.NewSpotScreen.route)
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