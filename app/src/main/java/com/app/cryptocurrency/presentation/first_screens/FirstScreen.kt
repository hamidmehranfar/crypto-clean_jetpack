package com.app.cryptocurrency.presentation.first_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cryptocurrency.R
import com.app.cryptocurrency.presentation.Screen

@Composable
fun FirstScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.first_screen_img),
            contentDescription = "welcome Image",
            modifier = Modifier
                .padding(32.dp)
                .size(width = 375.dp, height = 375.dp)
        )
        Text("Fast and Flexible\nTrading", style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.SemiBold
        ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(68.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.SignupScreen.route)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            ){
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.width(24.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            ){
                Text("Log in")
            }
        }
    }
}
