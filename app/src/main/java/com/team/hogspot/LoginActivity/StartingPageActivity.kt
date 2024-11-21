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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team.hogspot.R
import com.team.hogspot.composables.H1
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.composables.SecondaryButton
import com.team.hogspot.ui.theme.AppTheme

class StartingPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            MyLoginApplicationTheme {
                StartingPage(onSignUp = { toSignUpForm() }, onLogIn = { toLogInForm() })
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
fun StartingPagePreview() {
    AppTheme {
        StartingPage(onSignUp = {}, onLogIn = {})
    }
}

@Composable
fun StartingPage(onSignUp: () -> Unit, onLogIn: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101820)) // Dark background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Icon",
                modifier = Modifier.size(120.dp)
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
            Spacer(modifier = Modifier.height(200.dp))
            PrimaryButton(
                onClick=onSignUp,
                text="get started!",
                modifier=Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            SecondaryButton(
                onClick=onLogIn,
                text="log in",
                modifier=Modifier
            )
        }
    }
}