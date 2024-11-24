package com.team.hogspot.SearchActivity

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.team.hogspot.R
import com.team.hogspot.composables.Difficulty
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Hogspot
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.Navbar
import com.team.hogspot.composables.SearchItem
import com.team.hogspot.composables.User
import com.team.hogspot.ui.theme.AppTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                // Search()
            }
        }
    }

    // private fun


}



@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    AppTheme {
        SearchPage(onSearch = {})
    }
}

@Composable
fun SearchPage(
    onSearch: () -> Unit
) {
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
        Column (
            modifier = Modifier.fillMaxSize(),

        ){
            Header(
                pageTitle = "Search",
                username = "Jordi",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                type="Search",
                placeholder="Search Hogspot",
                iconId = R.drawable.search_icon,
            )
            Spacer(modifier = Modifier.height(32.dp))
            // map search results
            searchResults.forEach { hogspot ->
                SearchItem(
                    hogspot = hogspot,
                    onClick = {} // TODO: launch hogspot detailed activity with hogspot.id
                )
            }

            Navbar(
                activePage = "Search",
            )
        }
    }
}