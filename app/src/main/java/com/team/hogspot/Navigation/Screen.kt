package com.team.hogspot.Navigation

// sealed: only objects inside this class can inherit from it
sealed class Screen(val route: String) {
    object LandingScreen : Screen("landing_screen")
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")
    object ExploreScreen : Screen("explore_screen")
    object SearchScreen : Screen("search_screen")
    object UserScreen : Screen("user_screen")
    object DetailedSpotScreen : Screen("detailed_spot_screen")
    object NewSpotScreen : Screen("add_spot_screen")

    object PlayMapScreen : Screen("play_map_screen")
    object PlayDetailScreen : Screen("play_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}