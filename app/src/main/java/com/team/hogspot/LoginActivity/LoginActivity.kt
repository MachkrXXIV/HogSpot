package com.team.hogspot.LoginActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.composables.H1
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
                        id.toString()
                    },
                    onNavigate = {  }

                )
            }
        }
    }


}

@Composable
fun MyLoginApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = Color(0xFF101820)) {
            content()
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
                 id.toString()
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
            id.toString()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    onBackClick: () -> Unit,
    onLogin: (String, String) -> String,
    onNavigate: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("Username...") }
    var password by remember { mutableStateOf("Password...") }
    var isActive by remember { mutableStateOf(false) }

    if (username !== "Username..." && password !== "Password...") {
        isActive = true
    }
    if (username.isEmpty()) {
        username = "Username..."
    }
    if (password.isEmpty()) {
        password = "Password..."
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
            onValueChange = {
                username = it
                Log.d("LoginActivity", "updated username: $it")
            },
            shape = AppTheme.shape.containerRoundedTop,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = password,
            onValueChange = { password = it },
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
