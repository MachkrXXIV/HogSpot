package com.team.hogspot.LoginActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team.hogspot.Navigation.Navigation
import com.team.hogspot.Navigation.Screen
import com.team.hogspot.R
import com.team.hogspot.composables.H1
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.ui.theme.AppTheme

class LandingScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            AppTheme {
                Navigation()
            }
        }
    }

    private fun toSignUpForm() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun toLogInForm() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    AppTheme {
        Navigation()
    }
}

@Composable
fun LandingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background) // Dark background color
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            H1(
                text=""
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Icon",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                H1(
                    text = "HogSpot"
                )
                Spacer(modifier = Modifier.height(8.dp))
                H3(
                    text = "UARK geogussr.",
                    color = AppTheme.colorScheme.textSecondary
                )
            }


            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                PrimaryButton(
                    onClick={navController.navigate(Screen.SignupScreen.route)},
                    text="get started!",
                    shape = AppTheme.shape.container
                )
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryButton(
                    onClick={navController.navigate(Screen.LoginScreen.route)},
                    text="log in",
                    shape = AppTheme.shape.container
                )
            }
        }
    }
}