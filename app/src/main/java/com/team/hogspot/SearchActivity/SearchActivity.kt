package com.team.hogspot.SearchActivity

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.composables.Difficulty
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Hogspot
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SearchItem
import com.team.hogspot.ui.theme.AppTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                SearchPage(
                    onSearch = {},
                    userId = "1",
                    navController = null,
                )
            }
        }
    }

    // private fun


}



@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    AppTheme {
        SearchPage(
            onSearch = {},
            userId = "1",
            navController = null,
        )
    }
}

@Composable
fun SearchScreen(
    id: String,
    navController: NavController
) {
    SearchPage(
        onSearch = {},
        onUserClick = {
            navController.navigate(Screen.UserScreen.withArgs(id))
        },
        navController = navController,
        userId = id
    )
}

@Composable
fun SearchPage(
    onSearch: () -> Unit,
    onUserClick: () -> Unit = {},
    navController: NavController? = null,
    userId: String
) {
    var search by remember { mutableStateOf("Search...") }

    val searchResults = listOf(
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
                pageTitle = "Search",
                username = "Jordi",
                onUserClick = onUserClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                iconId = R.drawable.search_icon,
                value = search,
                onValueChange = {
                    search = it
                    Log.d("SearchActivity", "updated search: $search")
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            // map search results
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(575.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(10){
                    searchResults.forEach { hogspot ->
                        SearchItem(
                            hogspot = hogspot,
                            onClick = {
                               navController?.navigate(Screen.DetailedSpotScreen.withArgs(hogspot.id.toString()))
                            }
                        )
                    }
                }
            }

            Navbar(
                activePage = "Search",
                navController = navController,
                userId = userId
            )
        }
    }
}