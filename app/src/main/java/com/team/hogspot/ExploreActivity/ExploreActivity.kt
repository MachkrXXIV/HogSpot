package com.team.hogspot.ExploreActivity

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.LeaderBoard
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.user.User
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime

class ExploreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val mockSpots: List<GeoSpot> = listOf()
            val mockUser = User(
                userId = 1,
                userName = "Bob",
                email = "bob@gmail.com",
                dateJoined = LocalDateTime.now(),
                streak = 4,
                numSpots = 2,
                friends = listOf()
            )
            AppTheme {
                ExplorePage(
//                    mockSpots,
//                    mockUser,
                    navController = null,
                    userId = "1"
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun UserPreview() {
    val mockSpots: List<GeoSpot> =
        listOf(
            GeoSpot(
                geoSpotId = 1,
                name = "HogSpot 1",
                description = "This is a description of HogSpot 1",
                latitude = 0.0,
                longitude = 0.0,
                creatorId = 1,
                creationDate = LocalDateTime.now(),
                imgFilePath = "res/drawable/spot_image1.png",
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
            GeoSpot(
                geoSpotId = 2,
                name = "HogSpot 3",
                description = "This is a description of HogSpot 2",
                latitude = 0.0,
                longitude = 0.0,
                creatorId = 2,
                creationDate = LocalDateTime.now(),
                imgFilePath = "img2.jpg",
                difficulty = Difficulty.MEDIUM,
                hint = "This is a hint for HogSpot 2",
                rating = 3.5,
                )
        )
    val mockUser = User(
        userId = 1,
        userName = "Bob",
        email = "bob@gmail.com",
        dateJoined = LocalDateTime.now(),
        streak = 4,
        numSpots = 2,
        friends = listOf()
    )

    AppTheme {
        ExplorePage(
//            mockSpots,
//            mockUser,
            onUserClick = {
                Log.d("ExploreActivity", "User clicked")
            },
            navController = null,
            userId = "1"
        )
    }
}

@Composable
fun ExploreScreen(
    id: String,
    navController: NavController
) {
    ExplorePage(
        onUserClick = {
            navController.navigate(Screen.UserScreen.withArgs(id))
        },
        navController = navController,
        id
    )
}

@Composable
fun ExplorePage(
//    spots: List<GeoSpot>,
//    user: User,
    onUserClick: () -> Unit = {},
    navController: NavController? = null,
    userId: String,
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
            name = "HogSpot 1",
            description = "This is a description of HogSpot 1",
            latitude = 0.0,
            longitude = 0.0,
            creatorId = 1,
            creationDate = LocalDateTime.now(),
            imgFilePath = "res/drawable/spot_image1.png",
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
        GeoSpot(
            geoSpotId = 2,
            name = "HogSpot 3",
            description = "This is a description of HogSpot 2",
            latitude = 0.0,
            longitude = 0.0,
            creatorId = 2,
            creationDate = LocalDateTime.now(),
            imgFilePath = "img2.jpg",
            difficulty = Difficulty.MEDIUM,
            hint = "This is a hint for HogSpot 2",
            rating = 3.5,
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(AppTheme.size.medium)
    ) {
        Spacer(modifier = Modifier.height(AppTheme.size.medium))
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header(
                pageTitle = "Explore",
                username = user.userName,
                showBackButton = false,
                showUserProfile = true,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(670.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                H2(text = "New Spots")
                Spacer(modifier = Modifier.height(3.dp))
                SpotCarousel(
                    spots = spots,
                    onSpotClick = { spot ->
                        Log.d("ExploreActivity", "Spot clicked: $spot")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                H2(text = "Leaderboard")
                Spacer(modifier = Modifier.height(3.dp))
                LeaderBoard(users =
                    listOf(
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
                            userId = 3,
                            userName = "Stuart",
                            email = "stuart@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 17,
                            numSpots = 14,
                            friends = listOf()
                        ),
                        User(
                            userId = 4,
                            userName = "Gru",
                            email = "stuart@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 3,
                            numSpots = 3,
                            friends = listOf()
                        ),
                        User(
                            userId = 5,
                            userName = "Vector",
                            email = "stuart@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 12,
                            numSpots = 12,
                            friends = listOf()
                        ),
                        User(
                            userId = 6,
                            userName = "Agnes",
                            email = "bob@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 4,
                            numSpots = 2,
                            friends = listOf()
                        ),
                        User(
                            userId = 7,
                            userName = "Marco",
                            email = "bob@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 4,
                            numSpots = 2,
                            friends = listOf()
                        ),
                        User(
                            userId = 8,
                            userName = "Edith",
                            email = "bob@gmail.com",
                            dateJoined = LocalDateTime.now(),
                            streak = 4,
                            numSpots = 2,
                            friends = listOf()
                        )
                    )
                )
            }
            Navbar(
                activePage = "Explore",
                navController = navController,
                userId = userId
            )
        }
    }
}