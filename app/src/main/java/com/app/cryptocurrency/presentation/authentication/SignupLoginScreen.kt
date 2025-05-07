package com.app.cryptocurrency.presentation.authentication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.cryptocurrency.data.remote.dto.AuthType
import com.app.cryptocurrency.presentation.Screen
import com.app.cryptocurrency.presentation.home.UserViewModel

@Composable
fun SignupLoginScreen(
    navController: NavController,
    viewModel: SignupLoginViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    type: AuthType
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.state

    LaunchedEffect(state.user) {
        state.user?.let {
            userViewModel.setUser(state.user!!)
            navController.navigate(Screen.CoinListScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
            Spacer(modifier = Modifier.width(120.dp))
            Text(
                if(type == AuthType.Login) "Log in" else "Sign up",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 18.sp,
                )
            )
        }
        if(type == AuthType.Signup){
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                placeholder = { Text("Enter your Full Name") },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally).
                fillMaxWidth().padding(horizontal = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            placeholder = { Text("Enter your email address") },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).fillMaxWidth().padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).fillMaxWidth().padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = {
                if(email.isNotEmpty() && password.isNotEmpty()){
                    if (type == AuthType.Login) {
                        viewModel.login(email = email, password = password)
                    } else {
                        if(name.isNotEmpty()) {
                            viewModel.signup(name = name, email = email, password = password)
                        }
                    }
                }
            },
            enabled = !state.isLoading,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier.padding(horizontal = 24.dp).
            fillMaxWidth().height(56.dp)
        ){
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(36.dp))
                }
            } else {
                Text(
                    if (type == AuthType.Login) "Login" else "Sign up",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp)
                )
            }
        }
    }
}