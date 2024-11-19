package com.team.hogspot.LoginActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            MyLoginApplicationTheme {
                LoginForm(onBackClick = { backPressed() })
            }
        }
    }

    private fun backPressed() {
        onBackPressedDispatcher.onBackPressed()
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
    MyLoginApplicationTheme {
        LoginForm(onBackClick = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(onBackClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(35.dp)
    ) {
        TopAppBar(
            title = { Text("Back", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF101820))
        )
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            colors =
            textFieldColors(
                unfocusedContainerColor= Color(0xFF101820),
                focusedContainerColor = Color(0xFF1C2A3A),
                focusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors =
            textFieldColors(
                unfocusedContainerColor= Color(0xFF101820),
                focusedContainerColor = Color(0xFF1C2A3A),
                focusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle login */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC323E)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
