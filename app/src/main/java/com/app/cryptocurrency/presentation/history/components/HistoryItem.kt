package com.app.cryptocurrency.presentation.history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.app.cryptocurrency.domain.model.HistoryEntity
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun HistoryItem(
    history: HistoryEntity
) {
    val sdf: SimpleDateFormat = SimpleDateFormat("MMM dd,yyyy HH:mm")
    val resultDate : Date = Date(history.time)

    Column(modifier = Modifier.padding(horizontal = 16.dp)){
        Text(
            "created time : ${sdf.format(resultDate)}",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                "type : ${history.type}",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )
            Text(
                "amount : ${history.amount}",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(
                    model = history.image,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        history.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
            Text(
                "$${history.price}",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}