package com.app.cryptocurrency.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.app.cryptocurrency.common.Constants
import com.app.cryptocurrency.presentation.Screen
import com.app.cryptocurrency.presentation.coin_details.CoinDetailScreen
import com.app.cryptocurrency.presentation.coin_list.CoinListScreen
import com.app.cryptocurrency.presentation.history.HistoryListScreen

@Composable
fun HomeScreen() {
    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar (containerColor = MaterialTheme.colorScheme.onPrimary){
                val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Constants.BottomNavItems.forEach { navItem ->
                    NavigationBarItem(

                        selected = currentRoute == navItem.route,
                        onClick = {
                            if (currentRoute != navItem.route) {
                                homeNavController.navigate(navItem.route) {
                                    popUpTo(homeNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = homeNavController,
            startDestination = Constants.BottomNavItems.first().route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(homeNavController)
            }
            composable(Screen.CoinListScreen.route) {
                CoinListScreen(homeNavController)
            }
            composable(
                route = Screen.CoinDetailScreen.route + "/{symbol}"
            ) {
                CoinDetailScreen()
            }
            composable(
                route = Screen.HistoryScreen.route
            ) {
                HistoryListScreen(homeNavController)
            }
        }
    }
}
