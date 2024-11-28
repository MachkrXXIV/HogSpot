package com.team.hogspot.NewSpotActivity

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.team.hogspot.R
import com.team.hogspot.composables.Difficulty
import com.team.hogspot.composables.H1
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Hogspot
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.InputSize
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.P
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.composables.SearchItem
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.composables.SelectDifficulty
import com.team.hogspot.ui.theme.AppTheme



class NewSpotActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                NewSpotPage(
                    onBackClick = {},
                    onImageClick = {},
                    onSubmit = {}
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NewSpotPreview() {
    AppTheme {
        NewSpotPage(
            onBackClick = {},
            onImageClick = {},
            onSubmit = {}
        )
    }
}

@Composable
fun NewSpotPage(
    onBackClick: () -> Unit,
    onImageClick: () -> Unit,
    onSubmit: () -> Unit
) {
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
                pageTitle = "Upload",
                username = "Jordi",
                showBackButton = true,
                showUserProfile = false,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(670.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                H2(
                    text = "Title"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Input(
                    placeholder = "Title...",
                    type = "Title",
                    shape = AppTheme.shape.container,
                    iconId = -1,
                    size = InputSize.XS
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "Description"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Input(
                    placeholder = "Description...",
                    type = "Title",
                    shape = AppTheme.shape.container,
                    iconId = -1,
                    size = InputSize.XL
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "Location"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Input(
                    placeholder = "Location...",
                    type = "Title",
                    shape = AppTheme.shape.container,
                    iconId = -1,
                    size = InputSize.XS
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "Images"
                )
                Spacer(modifier = Modifier.height(4.dp))
                P(
                    text = "Upload 2-3 images of your HogSpot.",
                    color = AppTheme.colorScheme.textTertiary
                )
                Spacer(modifier = Modifier.height(8.dp))
                SecondaryButton(
                    onClick=onImageClick,
                    text="Image",
                    iconId=R.drawable.plus_icon,
                    shape=AppTheme.shape.container,
                    modifier=Modifier
                        .height(128.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                H2(
                    text = "Difficulty"
                )
                Spacer(modifier = Modifier.height(12.dp))
                SelectDifficulty(
                    onSelection={ difficulty ->
                        Log.d("NewSpotActivity", "Selected difficulty: $difficulty")
                    }
                )

                Spacer(modifier = Modifier.height(36.dp))

                PrimaryButton(
                    onClick=onSubmit,
                    text="Create HogSpot",
                    shape=AppTheme.shape.container,
                    modifier=Modifier
                        .height(64.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

            }
    

            Navbar(
                activePage = "Your Spots"
            )

        }
    }
}