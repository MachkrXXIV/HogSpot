package com.team.hogspot.ExploreActivity

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
import com.team.hogspot.R
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.composables.UserHeader
import com.team.hogspot.composables.UserInfoCards
import com.team.hogspot.composables.UserTemp
import com.team.hogspot.ui.theme.AppTheme

class ExploreActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                ExplorePage()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun UserPreview() {
    AppTheme {
        ExplorePage()
    }
}


@Composable
fun ExplorePage() {

    val user = UserTemp(
        id = 1,
        username = "Jordi Castro",
        email = "jordi@gmail.com",
        dateJoined = "10/12/24",
        streak = 5,
        numSpots = 3,
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
            .background(AppTheme.colorScheme.background)
            .padding(AppTheme.size.medium)
    ) {
        Spacer(modifier = Modifier.height(AppTheme.size.medium))
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header(
                pageTitle = "Explore",
                username = "Jordi",
                showBackButton = true,
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
                H2(text = "Trending Spots")
                Spacer(modifier = Modifier.height(3.dp))
                SpotCarousel(
                    //need to grab top rated spots from database, assume just
                    // loop through creating list of 5-10 top rated ones in model
                    spots = user.spots
                )
                Spacer(modifier = Modifier.height(16.dp))
                H2(text = "Leaderboard")
                Spacer(modifier = Modifier.height(3.dp))

            }

            Navbar(
                activePage = "Explore",
            )
        }
    }
}