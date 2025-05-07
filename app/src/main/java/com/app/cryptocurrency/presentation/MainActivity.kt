package com.app.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cryptocurrency.R
import com.app.cryptocurrency.data.remote.dto.AuthType
import com.app.cryptocurrency.presentation.authentication.SignupLoginScreen
import com.app.cryptocurrency.presentation.first_screens.FirstScreen
import com.app.cryptocurrency.presentation.first_screens.WelcomeScreen
import com.app.cryptocurrency.presentation.home.HomeScreen
import com.app.cryptocurrency.presentation.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WelcomeScreen.route
                    ) {
                        composable(
                            route = Screen.WelcomeScreen.route
                        ) {
                            WelcomeScreen(navController, currentScreen = 1, image = R.drawable.welcome_img, text = "")
                        }
                        composable(
                            route = Screen.SecondWelcomeScreen.route
                        ) {
                            WelcomeScreen(navController, currentScreen = 2, image = R.drawable.secure_transaction_img, text = "Transaction\nSecurity")
                        }
                        composable(
                            route = Screen.ThirdWelcomeScreen.route
                        ) {
                            WelcomeScreen(navController, currentScreen = 3, image = R.drawable.fast_reliable_img, text = "Fast And Reliable\nMarket Updated")
                        }
                        composable(
                            route = Screen.FirstScreen.route
                        ) {
                            FirstScreen(navController)
                        }
                        composable(
                            route = Screen.SignupScreen.route
                        ) {
                            SignupLoginScreen(navController, type = AuthType.Signup)
                        }
                        composable(
                            route = Screen.LoginScreen.route
                        ) {
                            SignupLoginScreen(navController, type = AuthType.Login)
                        }
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}