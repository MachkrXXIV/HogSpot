package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.model.user.User
import com.team.hogspot.ui.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@Composable
fun UserHeader(
    username: String,
    dateJoined: String,
) {
    val formattedDate = formatDate(dateJoined)
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        ProfileImage(
            username = username,
            onClick = {},
            size = ImageSize.XL
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
        ) {
            H2(
                text = username,
            )
            P(
                text = "Joined $formattedDate",
                color = AppTheme.colorScheme.textSecondary
            )

        }
    }

}

fun formatDate(dateJoined: String): String {
    // map month -> month name
    // get year
    // return month name + year
    val monthNum = dateJoined.substring(0, 2)
    val month = when (monthNum) {
        "01" -> "January"
        "02" -> "February"
        "03" -> "March"
        "04" -> "April"
        "05" -> "May"
        "06" -> "June"
        "07" -> "July"
        "08" -> "August"
        "09" -> "September"
        "10" -> "October"
        "11" -> "November"
        "12" -> "December"
        else -> "Invalid month"
    }
    val year = dateJoined.substring(6)
    return "$month $year"
}


@Composable
fun UserInfoCards(
    user: User
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserInfoCard(
            primaryText = user.streak.toString(),
            secondaryText = "day streak"
        )
        UserInfoCard(
            primaryText = user.numSpots.toString(),
            secondaryText = "spots"
        )

    }
    Spacer(modifier = Modifier.height(24.dp))
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserInfoCard(
            primaryText = user.friends.size.toString(),
            secondaryText = "friends"
        )

        UserInfoCard(
            primaryText = user.dateJoined.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            secondaryText = "date joined"
        )

    }
}

@Composable
fun UserInfoCard(
    primaryText: String = "",
    secondaryText: String = "",
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(80.dp)
            .border(2.dp, AppTheme.colorScheme.textTertiary, AppTheme.shape.container)

            .clip(AppTheme.shape.container)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            H3(
                text = primaryText,
                color = AppTheme.colorScheme.textPrimary
            )
            P(
                text = secondaryText,
                color = AppTheme.colorScheme.textSecondary
            )

        }
    }
}

@Composable
fun UserLeaderboardCard(
    position: Int,
    user: User
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Rank
            H2(
                text = position.toString(),
                color = AppTheme.colorScheme.textPrimary
            )
            Spacer(modifier = Modifier.width(50.dp))
            // Profile Icon
            ProfileImage(
                username = user.userName,
                onClick = {},
                size = ImageSize.SM
            )
            Spacer(modifier = Modifier.width(16.dp))
            H2(
                text = user.userName,
                color = AppTheme.colorScheme.textPrimary
            )
            Spacer(modifier = Modifier.weight(1f))
            H3(
                text = user.numSpots.toString() + "xp",
                color = AppTheme.colorScheme.textSecondary
            )
        }
}

@Composable
fun LeaderBoard(
    users: List<User>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .clip(AppTheme.shape.container)
            .background(AppTheme.colorScheme.backgroundSecondary)
    ) {
        items(users) { user ->
            UserLeaderboardCard(
                position = users.indexOf(user) + 1,
                user = user
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun UserCompsPreview() {
    val users = listOf(
        User(
            userId = 1,
            userName = "Jordi Castro",
            email = "jordi@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 5,
            numSpots = 3,
            friends = listOf()
        ),
        User(
            userId = 2,
            userName = "Jordi Castro",
            email = "jordi@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 5,
            numSpots = 3,
            friends = listOf()
        ),
        User(
            userId = 3,
            userName = "Jordi Castro",
            email = "jordi@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 5,
            numSpots = 3,
            friends = listOf()
        ),
        User(
            userId = 4,
            userName = "Jordi Castro",
            email = "jordi@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 5,
            numSpots = 3,
            friends = listOf()
        ),
        User(
            userId = 5,
            userName = "Jordi Castro",
            email = "jordi@gmail.com",
            dateJoined = LocalDateTime.now(),
            streak = 5,
            numSpots = 3,
            friends = listOf()
        )

    )
    val user = User(
        userId = 1,
        userName = "Jordi Castro",
        email = "jordi@gmail.com",
        dateJoined = LocalDateTime.now(),
        streak = 5,
        numSpots = 3,
        friends = listOf()
    )

    AppTheme{
        Column(
            modifier = Modifier
                .background(AppTheme.colorScheme.background)
                .padding(AppTheme.size.medium)
                .fillMaxSize(),


        ) {
            UserHeader(
                username = user.userName,
                dateJoined = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(user.dateJoined)
            )
            Spacer(modifier = Modifier.height(16.dp))
            UserInfoCards(
                user = user
            )
            Spacer(modifier = Modifier.height(16.dp))
            UserLeaderboardCard(
                1,
                user = user
            )
            LeaderBoard(users = users)
        }
    }

}