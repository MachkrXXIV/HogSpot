package com.team.hogspot.Navigation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.team.hogspot.DetailedSpotActivity.DetailedSpotScreen
import com.team.hogspot.LoginActivity.LandingScreen
import com.team.hogspot.LoginActivity.LoginScreen
import com.team.hogspot.LoginActivity.SignUpScreen
import com.team.hogspot.NewSpotActivity.NewSpotScreen
import com.team.hogspot.PlayActivity.PlayActivity
import com.team.hogspot.SearchActivity.SearchScreen
import com.team.hogspot.UserActivity.UserScreen
import com.team.hogspot.composables.Navbar
import com.team.hogspot.ui.theme.AppTheme


@Composable
fun Navigation(startDestination: String = Screen.LandingScreen.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        // Add destinations here
        composable(route = Screen.LandingScreen.route) {
            LandingScreen(navController)
        }
        composable(route = Screen.SignupScreen.route) {
            SignUpScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.ExploreScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            ExploreScreen(entry.arguments?.getString("id"), navController)

        }

        composable(
            route = Screen.SearchScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("id")?.let { SearchScreen(it, navController) }

        }

        composable(
            route = Screen.UserScreen.route + "/{id}/{createdSpot}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("createdSpot") {
                    type = NavType.StringType
                    defaultValue = "false"
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("id")?.let {
                UserScreen(
                    it,
                    entry.arguments?.getString("createdSpot") ?: "false",
                    navController,
                )
            }

        }

        composable(
            route = Screen.NewSpotScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                },
            )
        ) {
            NewSpotScreen(it.arguments?.getString("id") ?: "", navController)
        }

        composable(
            route = Screen.DetailedSpotScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("id")?.let { DetailedSpotScreen(it, navController) }
        }

        composable(
            route = Screen.PlayScreen.route + "/{id}"
        ) {
            val context = LocalContext.current
            val intent = Intent(context, PlayActivity::class.java)
            context.startActivity(intent)
        }

    }
}

@Composable // TODO: remove this after integrating the explore screen
fun ExploreScreen(id: String?, navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(AppTheme.size.medium)
    ) {
        Text("Explore Screen, id: $id")

        if (id != null) {
            Navbar(
                activePage = "Explore",
                navController = navController,
                userId = id

            )
        }

    }
    // use the id to fetch the user data
    // pass in the user data to the header -> profile image
}

