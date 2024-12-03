package com.team.hogspot.Navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.team.hogspot.DetailedSpotActivity.DetailedSpotScreen
import com.team.hogspot.ExploreActivity.ExploreScreen
import com.team.hogspot.LoginActivity.LandingScreen
import com.team.hogspot.LoginActivity.LoginScreen
import com.team.hogspot.LoginActivity.SignUpScreen
import com.team.hogspot.NewSpotActivity.NewSpotScreen
import com.team.hogspot.PlayActivity.PlayActivity
import com.team.hogspot.PlayActivity.PlayScreen
import com.team.hogspot.SearchActivity.SearchScreen
import com.team.hogspot.UserActivity.UserScreen


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
            entry.arguments?.getString("id")?.let { ExploreScreen(it, navController) }

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
            route = Screen.UserScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("id")?.let { UserScreen(it, navController) }

        }

        composable(route = Screen.NewSpotScreen.route) {
            NewSpotScreen(navController)
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

