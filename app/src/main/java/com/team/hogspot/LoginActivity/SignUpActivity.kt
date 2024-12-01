package com.team.hogspot.LoginActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.App
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.UserViewModel
import com.team.hogspot.UserViewModelFactory
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.InputSize
import com.team.hogspot.composables.P
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.ui.theme.AppTheme

class SignUpActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            AppTheme {
                SignUpForm(
                    onBackClick = { },
                    onSignUp = { email, username ->
                        val id = insertIntoDB(email, username)
                        id.toString()
                    },
                    onNavigate = {  },
                    onNavigateToLogin = {  }
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SignUpFormPreview() {
    AppTheme {
         SignUpForm(
            onBackClick = { },
            onSignUp = { email, username ->
                val id = insertIntoDB(email, username)
                id.toString()
            },
            onNavigate = {  },
            onNavigateToLogin = {  }
         )
    }
}

@Composable
fun SignUpScreen(navController: NavController) {
    SignUpForm(
        onBackClick = {navController.popBackStack() },
        onSignUp = { email, username ->
            val id = insertIntoDB(email, username)
            id.toString()
        },
        onNavigate = { id ->
            navController.navigate(Screen.ExploreScreen.withArgs(id))
        },
        onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
    )
}

fun insertIntoDB(email: String, username: String): String {
    // TODO: insert into db
    // val id = userViewModel.insert(email, username)
    // right now, return a dummy id
    return "1"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(
    onBackClick: () -> Unit = {},
    onSignUp: (String, String) -> String,
    onNavigate: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("Email...") }
    var username by remember { mutableStateOf("Username...") }
    var password by remember { mutableStateOf("Password...") }
    var confirmPassword by remember { mutableStateOf("Confirm Password...") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(35.dp)
    ) {
        Header(
            pageTitle = "Sign up.",
            showBackButton = true,
            showUserProfile = false,
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(32.dp))
        Input(
            value = email,
            onValueChange = { email = it },
            shape = AppTheme.shape.containerRoundedTop,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = username,
            onValueChange = { username = it},
            shape = AppTheme.shape.containerRoundedNone,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = password,
            onValueChange = { password = it},
            shape = AppTheme.shape.containerRoundedNone,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                Log.d("SignUpActivity", "updated confirmPassword: $confirmPassword")
            },
            shape = AppTheme.shape.containerRoundedBottom,
            iconId = -1,
            size = InputSize.XS
        )
        Spacer(modifier = Modifier.height(64.dp))
        PrimaryButton(
            text = "SIGN UP",
            onClick = {
                val id: String = onSignUp(email, username)
                if (id != "-1")
                    onNavigate(id)
            },
            shape = AppTheme.shape.container,
            isActive = false,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.clickable { onNavigateToLogin() },
                contentAlignment = Alignment.Center
            ) {
                P(
                    text = "Already have an account? Login",
                    color = AppTheme.colorScheme.textSecondary,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    }
}
