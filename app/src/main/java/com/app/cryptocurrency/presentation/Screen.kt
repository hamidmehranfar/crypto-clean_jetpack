package com.app.cryptocurrency.presentation

sealed class Screen(val route: String) {
    data object WelcomeScreen: Screen("welcome_screen")
    data object SecondWelcomeScreen: Screen("second_welcome_screen")
    data object ThirdWelcomeScreen: Screen("third_welcome_screen")
    data object FirstScreen: Screen("first_screen")
    data object SignupScreen: Screen("signup_screen")
    data object LoginScreen: Screen("login_screen")
    data object ProfileScreen : Screen("profile_screen")
    data object CoinListScreen: Screen("coin_list_screen")
    data object CoinDetailScreen: Screen("coin_detail_screen")
    data object HistoryScreen: Screen("history_screen")
}
