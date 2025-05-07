package com.app.cryptocurrency.presentation.first_screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeTextSection(){
    Text(
        text = buildAnnotatedString {
            append("Welcome to ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append("\nPool")
            }
            append("Ex")
        },
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.SemiBold
        ),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
    )
}