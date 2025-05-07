package com.app.cryptocurrency.presentation.first_screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cryptocurrency.presentation.Screen
import com.app.cryptocurrency.presentation.first_screens.components.WelcomeTextSection

@Composable
fun WelcomeScreen(
    navController: NavController,
    totalScreens: Int = 4,
    currentScreen: Int,
    @DrawableRes image: Int,
    text :  String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(onClick = {
                navController.navigate(Screen.FirstScreen.route)
            }) {
                Text(
                    text = "skip",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Image(
            painter = painterResource(image),
            contentDescription = "welcome Image",
            modifier = Modifier
                .padding(32.dp)
                .size(width = 375.dp, height = 375.dp)
        )
        if(currentScreen == 1){
            WelcomeTextSection()
        }
        else{
            Text(text, style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                (0 until totalScreens).forEach { index ->
                    Box(
                        modifier = Modifier
                            .size(
                                width = if (index == currentScreen - 1) 36.dp else 16.dp,
                                height = 12.dp
                            )
                            .padding(4.dp)
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = if (index == currentScreen - 1) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.tertiary
                                }
                            )
                    )
                }
            }

            IconButton(
                onClick = {
                    if(currentScreen == 1){
                        navController.navigate(Screen.SecondWelcomeScreen.route)
                    }
                    else if(currentScreen == 2){
                        navController.navigate(Screen.ThirdWelcomeScreen.route)
                    }
                    else if(currentScreen == 3){
                        navController.navigate(Screen.FirstScreen.route)
                    }
                },
                modifier = Modifier
                    .size(48.dp) // Adjust size as needed
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary // Arrow color
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next"
                )
            }
        }
    }
}
