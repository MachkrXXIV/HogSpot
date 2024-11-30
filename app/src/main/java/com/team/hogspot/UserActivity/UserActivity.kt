package com.team.hogspot.UserActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.team.hogspot.App
import com.team.hogspot.R
import com.team.hogspot.UserViewModel
import com.team.hogspot.UserViewModelFactory
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.composables.SpotCarousel
import com.team.hogspot.composables.UserHeader
import com.team.hogspot.composables.UserInfoCards
import com.team.hogspot.model.user.UserRepository
import com.team.hogspot.ui.theme.AppTheme
import java.time.format.DateTimeFormatter

class UserActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userRepository)
    }
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                UserPage(userViewModel)
            }
        }
    }

    // private fun
}


@Preview(showBackground = true)
@Composable
fun UserPreview() {
    lateinit var mockViewModel: UserViewModel
    AppTheme {
        UserPage(mockViewModel)
    }
}


@Composable
fun UserPage(viewModel: UserViewModel) {
    val user = viewModel.currentUser.value

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
                username = user!!.userName,
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
                    username = user!!.userName,
                    dateJoined = user?.dateJoined?.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()
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
                    spots = viewModel.savedGeoSpots.value!!
                )

                Spacer(modifier = Modifier.height(36.dp))

                SecondaryButton(
                    onClick = {},
                    text = "New HogSpot",
                    iconId = R.drawable.plus_icon,
                    shape = AppTheme.shape.container,
                    modifier = Modifier
                        .height(64.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

            }

            Navbar(
                activePage = "Your Spots",
            )

        }
    }

}