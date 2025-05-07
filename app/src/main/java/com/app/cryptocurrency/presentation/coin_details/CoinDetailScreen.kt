package com.app.cryptocurrency.presentation.coin_details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.cryptocurrency.data.remote.dto.TabType
import com.app.cryptocurrency.presentation.Screen
import com.app.cryptocurrency.presentation.coin_details.components.TabSection

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val operationState = viewModel.operationState.value

    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(TabType.Buy.name, TabType.Sell.name)

    LaunchedEffect(operationState.success) {
        operationState.success.let {
            if(operationState.success == true){
//                Toast.makeText( "operation was successful", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                TabRow(
                    selectedTabIndex = tabIndex,
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> TabSection(type = TabType.Buy, coin = coin)
                    1 -> TabSection(type = TabType.Sell, coin = coin)
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(36.dp))
            }
        }
    }
}