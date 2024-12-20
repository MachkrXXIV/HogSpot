package com.team.hogspot.LoginActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.InputSize
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.ui.theme.AppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            AppTheme {
                LoginForm(
                    onBackClick = {  },
                    onLogin = { username, password ->
                        val id = login(username, password)
                        id
                    },
                    onNavigate = {  }

                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    AppTheme {
         LoginForm(
             onBackClick = {},
             onLogin = { username, password ->
                 val id = login(username, password)
                 id
             },
             onNavigate = {}
         )
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    LoginForm(
        onBackClick = { navController.popBackStack() },
        onLogin = { username, password ->
            val id = login(username, password)
            id
        },
        onNavigate = { id ->
            navController.navigate(Screen.ExploreScreen.withArgs(id))
        }
    )
}

fun login(username: String, password: String): String {
    // TODO: search for user in database
    // right now, return a dummy id
    return "2"
}

@Composable
fun LoginForm(
    onBackClick: () -> Unit,
    onLogin: (String, String) -> String,
    onNavigate: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    if (username !== "" && password !== "") {
        isActive = true
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(AppTheme.size.large)
    ) {
        Header(
            pageTitle = "Welcome back.",
            showBackButton = true,
            showUserProfile = false,
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Input(
            value = username,
            label = "Username",
            onValueChange = { username = it},
            shape = AppTheme.shape.containerRoundedTop,
            iconId = -1,
            size = InputSize.XS,
        )
        Input(
            value = password,
            label = "Password",
            onValueChange = { password = it},
            shape = AppTheme.shape.containerRoundedBottom,
            iconId = -1,
            size = InputSize.XS
        )

        Spacer(modifier = Modifier.height(64.dp))

        PrimaryButton(
            text = "LOGIN",
            onClick = {
                if (isActive) {
                    val id = onLogin(username, password)
                    if (id != "-1")
                        onNavigate(id)
                } else {
                    Toast.makeText(context, "Fill out all required fields.", Toast.LENGTH_LONG).show()
                }
            },
            shape = AppTheme.shape.container,
            isActive = isActive,
        )

        Spacer(modifier = Modifier.height(24.dp))

        H3(
            text = "FORGOT PASSWORD",
            color = AppTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(128.dp))

        SecondaryButton(
            text = "Login With Google",
            onClick = {},
            iconId = R.drawable.google_icon,
            shape = AppTheme.shape.container,
            textColor = AppTheme.colorScheme.textPrimary,
            horizontalArrangement = Arrangement.Start,
        )
    }
}
