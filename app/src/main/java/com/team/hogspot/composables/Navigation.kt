package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.team.hogspot.R
import com.team.hogspot.ui.theme.AppTheme
import coil3.compose.rememberAsyncImagePainter
import coil3.svg.SvgImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun Header (
    pageTitle: String,
    showBackButton: Boolean = false,
    user: User,
    onUserClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(AppTheme.colorScheme.background)
            .padding(horizontal = 16.dp), // Apply horizontal padding
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (showBackButton) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp) // Set the size to match the icon
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_icon),
                    contentDescription = "Back Button",
                    tint = AppTheme.colorScheme.textPrimary,
                    modifier = Modifier.size(24.dp) // Set the size to match the button
                )
            }
        } else {
            // Add a spacer to keep the title centered
            Spacer(modifier = Modifier.size(24.dp))
        }

        H3(
            text = pageTitle,
            color = AppTheme.colorScheme.textPrimary,
        )

        ProfileImage(
            user = user,
            onClick = onUserClick,
            size = ImageSize.SMALL
        )

    }
}

enum class ImageSize {
    SMALL,
    LARGE
}

@Composable
fun ProfileImage(
    user: User,
    onClick: () -> Unit,
    size: ImageSize
) {
    // draw a circle with the first letter of the user's name
    // pick a random color for the circle from AppTheme.colorScheme.profileColors
    val color = AppTheme.colorScheme.profileColors.random()
    val letter = user.name.first().uppercaseChar()
    val sizeDp = when (size) {
        ImageSize.SMALL -> 28.dp
        ImageSize.LARGE -> 48.dp
    }

    Box(
        modifier = Modifier
            .size(sizeDp)
            .background(color, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = AppTheme.colorScheme.background,
            style = AppTheme.typography.h3
        )
    }

}

@Composable
fun NavItem(
    text: String,
    onClick: () -> Unit,
    iconId: Int,
    modifier: Modifier = Modifier,

) {
    var isActive = false
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (isActive) AppTheme.colorScheme.textPrimary else AppTheme.colorScheme.textSecondary
        ),
        modifier = modifier


    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "HogSpot Logo",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                fontFamily = AppTheme.typography.labelNormal.fontFamily,
                fontSize = 14.sp
            )
        }
    }
}

interface NavItemProps {
    val text: String
    val onClick: () -> Unit
    val iconId: Int
}

interface User {
    val name: String
}

@Composable
fun Navbar(
    items: List<NavItemProps>,
    modifier: Modifier = Modifier
) {
//    PreviewImage()
    Spacer(modifier = Modifier.width(16.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.background)
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEach { item ->
            NavItem(
                text = item.text,
                onClick = item.onClick,
                iconId = item.iconId
            )
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewNavigation() {
    AppTheme {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(AppTheme.colorScheme.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Header(
                pageTitle = "Explore",
                showBackButton = true,
                user = object : User {
                    override val name = "Jordi Castro"
                },
                onUserClick = {},
                onBackClick = {}
            )

            Navbar(
                items = listOf(
                    object : NavItemProps {
                        override val text = "Explore"
                        override val onClick = {}
                        override val iconId = R.drawable.compass_icon
                    },
                    object : NavItemProps {
                        override val text = "Search"
                        override val onClick = {}
                        override val iconId = R.drawable.search_icon

                    },
                    object : NavItemProps {
                        override val text = "Your Spots"
                        override val onClick = {}
                        override val iconId = R.drawable.map_icon
                    }
                )
            )

        }


    }
}