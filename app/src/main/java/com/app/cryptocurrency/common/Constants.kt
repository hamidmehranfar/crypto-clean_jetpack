package com.app.cryptocurrency.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import com.app.cryptocurrency.data.remote.dto.BottomNavItem
import com.app.cryptocurrency.presentation.Screen

object Constants {

    const val BASE_URL = "https://cryptotest.pythonanywhere.com"

    const val PARAM_COIN_ID = "symbol"

    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Outlined.Home,
            route = Screen.CoinListScreen.route
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = Screen.ProfileScreen.route
        ),
        BottomNavItem(
            label = "Market",
            icon = Icons.Outlined.ShoppingCart,
            route = Screen.CoinDetailScreen.route + "/USDT"
        ),
    )
}