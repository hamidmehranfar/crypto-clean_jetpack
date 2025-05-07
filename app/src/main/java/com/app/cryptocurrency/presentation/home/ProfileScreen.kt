package com.app.cryptocurrency.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.cryptocurrency.R
import com.app.cryptocurrency.presentation.Screen
import com.app.cryptocurrency.presentation.home.components.HomeItem

@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val user by userViewModel.user
    Column(
        modifier = Modifier
            .fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.background(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primary
            ).
            fillMaxWidth()
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "my icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    user?.name ?: "", style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ))
                Spacer(modifier = Modifier.height(2.dp))
                Text(user?.email ?: "", style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                ))
                Spacer(modifier = Modifier.height(2.dp))
                Text("balance : ${user?.balance ?: ""}", style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                ))
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        HomeItem(
            navController = navController,
            text = "History",
            icon = Icons.Filled.Info,
            destination = Screen.HistoryScreen.route,
        )
        Spacer(modifier = Modifier.height(40.dp))
        HomeItem(
            navController = navController,
            text = "Bank Details",
            icon = Icons.Filled.Home,
            destination = "",
        )
        Spacer(modifier = Modifier.height(40.dp))
        HomeItem(
            navController = navController,
            text = "Notifications",
            icon = Icons.Filled.Notifications,
            destination = "",
        )
        Spacer(modifier = Modifier.height(40.dp))
        HomeItem(
            navController = navController,
            text = "Security",
            icon = Icons.Filled.Settings,
            destination = "",
        )
        Spacer(modifier = Modifier.height(40.dp))
        HomeItem(
            navController = navController,
            text = "Help And Support",
            icon = Icons.Sharp.CheckCircle,
            destination = "",
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextButton(
            onClick = {

            }
        ) {
            Text(
                "Logout",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 18.sp,
                )
            )
        }
    }
}