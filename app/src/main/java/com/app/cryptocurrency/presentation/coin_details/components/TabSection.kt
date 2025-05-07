package com.app.cryptocurrency.presentation.coin_details.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.app.cryptocurrency.data.remote.dto.AuthType
import com.app.cryptocurrency.data.remote.dto.TabType
import com.app.cryptocurrency.domain.model.Coin
import com.app.cryptocurrency.presentation.coin_details.CoinDetailViewModel
import com.app.cryptocurrency.presentation.home.UserViewModel

@Composable
fun TabSection(
    type: TabType,
    coin: Coin,
    coinViewModal: CoinDetailViewModel = hiltViewModel(),
    userViewModal: UserViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val state by coinViewModal.operationState

    val user by userViewModal.user

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = coin.imageUrl,
                contentDescription = "image",
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(coin.name, style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("price : ${coin.price}", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            placeholder = { Text("Enter crypto amount") },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth().padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            placeholder = { Text("Enter crypto wallet adress") },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth().padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = {
                if(amount.isNotEmpty() && address.isNotEmpty()){
                    val email = user?.email
                    coinViewModal.buySellRequest(email = email.toString(), amount = amount.toDouble(),
                        coin = coin , address = address , type = type)
                }
            },
            enabled = !state.isLoading,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth().height(56.dp)
        ){
            if(state.isLoading){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(36.dp))
                }
            }
            else {
                Text(
                    if(type == TabType.Sell) "Sell" else "Buy",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}