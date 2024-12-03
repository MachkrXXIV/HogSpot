package com.team.hogspot.LoginActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.Input
import com.team.hogspot.composables.InputSize
import com.team.hogspot.composables.P
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.ui.theme.AppTheme

class SignUpActivity : ComponentActivity() {
//    private val userViewModel: UserViewModel by viewModels {
//        UserViewModelFactory((application as App).userRepository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            AppTheme {
                SignUpForm(
                    onBackClick = { },
                    onSignUp = { email, username ->
                        val id = insertIntoDB(email, username)
                        id
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
                id
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
            id
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

@Composable
fun SignUpForm(
    onBackClick: () -> Unit = {},
    onSignUp: (String, String) -> String,
    onNavigate: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
        isActive = true
    }

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
            label = "Email",
            onValueChange = { email = it },
            shape = AppTheme.shape.containerRoundedTop,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = username,
            label = "Username",
            onValueChange = { username = it},
            shape = AppTheme.shape.containerRoundedNone,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = password,
            label = "Password",
            onValueChange = { password = it},
            shape = AppTheme.shape.containerRoundedNone,
            iconId = -1,
            size = InputSize.XS
        )
        Input(
            value = confirmPassword,
            label = "Confirm Password",
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
                if (isActive) {
                    val id: String = onSignUp(email, username)
                    if (id != "-1")
                        onNavigate(id)
                } else {
                    Toast.makeText(context, "Fill out all required fields.", Toast.LENGTH_LONG).show()
                }
            },
            shape = AppTheme.shape.container,
            isActive = isActive,
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
