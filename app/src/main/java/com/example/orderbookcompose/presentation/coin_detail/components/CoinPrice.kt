package com.example.orderbookcompose.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CoinPrice(
    price: Float?
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.Green,
            shape = RoundedCornerShape(10.dp)
        )
        .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Price: ",
                color = Color.Green,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = price?.toString() ?: "No price information found.",
                color = Color.Green,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = if(price == null) "" else " USD",
                color = Color.Green,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
        }

    }

}