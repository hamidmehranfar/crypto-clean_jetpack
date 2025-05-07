package com.app.cryptocurrency.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeItem(
    navController: NavController,
    text : String,
    icon : ImageVector,
    destination: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if(destination.isNotEmpty())
                    navController.navigate(destination)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "icon",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "icon",
            modifier = Modifier.size(width = 30.dp, height = 30.dp),
            tint = MaterialTheme.colorScheme.outline
        )
    }
}